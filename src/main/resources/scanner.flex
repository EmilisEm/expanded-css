package scanner;

import java_cup.runtime.Symbol;
import scanner.ParseUtils;
import cssParser.sym;
import java.io.IOException;

%%

%class Lexer
%public
%cup
%type Symbol
%caseless

%line
%column

%state SELECTOR
%state ATTRIBUTE
%state ATTRIBUTE_VALUE
%state INHERATANCE
%state CONDITIONAL_CONDITION_TYPE
%state CONDITIONAL_CONDITION_VALUE
%state CONDITIONAL_VALUE1
%state CONDITIONAL_VALUE2
%state FONT_FAMILY

digit            = [0-9]
non_zero_digit   = [1-9]
letter           = [A-Za-z]
whitespace       = [ \t\n\r]+
tag              = BODY|DIV|SPAN|TABLE|TD|TH|IMG|H1|H2|H3|P|A|PRE|DT|DD|DO|UL|:hover
query_name       = Aural|Screen
size_condition   = (min|max)-(width|height)
orientation      = orientation
size_unit        = (px|rem|em)
orientation_type = (vertical|horizontal)
font_family      = FONT-FAMILY
gap              = [ ]

digit_16         = ({digit}|[a-f]|[A-F])
number           = ({non_zero_digit}({digit})*)|0
identifier       = ({letter}|_{1, 2}|-)({letter}|{digit}|_|-)*
class            = \.{identifier}
id               = #{identifier}
media            = @media[ ]{query_name}
url              = url\((({letter}|{digit}|:|\/|-|_|\.|\\)*)\)
attribute        = {identifier}
selector         = {id}|{tag}|{class}
size_mesurement  = {number}{size_unit}
color            = #(({digit_16}{6})|({digit_16}{3}))
percent_value    = {number}%
attribute_value  = {identifier}|{size_mesurement}|{color}|{percent_value}|{number}
sentence         = ({identifier}[ ]?)+
font             = (\"{sentence}\")|{identifier}

%%
<YYINITIAL> {
    "(" {return ParseUtils.symbol(sym.LPAR);}
    ")" {return ParseUtils.symbol(sym.RPAR);}
    "," {return ParseUtils.symbol(sym.COM);}
    "{" {return ParseUtils.symbol(sym.LBRACE);}
    "}" {return ParseUtils.symbol(sym.RBRACE);}

    {media} {return ParseUtils.symbol(sym.MEDIA, ParseUtils.parseMedia(yytext()));}
    {selector} {
        yybegin(SELECTOR);
        return ParseUtils.getSelector(yytext());

    }
    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token at " + yyline + ":" + yycolumn);}
}

<SELECTOR> {
    "!important" {return ParseUtils.symbol(sym.IMPORTANT);}
    ">"/{selector} {return ParseUtils.symbol(sym.RAROW);}
    "(" {
        yybegin(INHERATANCE);
        return ParseUtils.symbol(sym.START_INH);
    }
    "{"/{whitespace}*({attribute}|}) {
        yybegin(ATTRIBUTE);
        return ParseUtils.symbol(sym.LBRACE);
    }
    {gap}/{selector} {return ParseUtils.symbol(sym.CHILD_SELECTOR);}
    {selector} {return ParseUtils.getSelector(yytext());}
    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in selector at " + yyline + ":" + yycolumn);}
}

<INHERATANCE> {
    "," {return ParseUtils.symbol(sym.COM);}
    ")" {
        yybegin(SELECTOR);
        return ParseUtils.symbol(sym.END_INH);
    }
    {selector}/[,)] {return ParseUtils.getSelector(yytext());}
    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in inheratance at " + yyline + ":" + yycolumn);}
}

<ATTRIBUTE> {
    ":" {
        yybegin(ATTRIBUTE_VALUE);
        return ParseUtils.symbol(sym.COL);
    }
    "}" {
        yybegin(YYINITIAL);
        return ParseUtils.symbol(sym.RBRACE);
    }
    {font_family}/{whitespace}*:{whitespace}*{font} {
        yybegin(FONT_FAMILY);
        return ParseUtils.symbol(sym.FONT_FAMILY);
    }
    {attribute}/{whitespace}*: {return ParseUtils.symbol(sym.ATTRIBUTE, yytext());}
    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in attribute at " + yyline + ":" + yycolumn);}
}

<FONT_FAMILY> {
    ";" {
        yybegin(ATTRIBUTE);
        return ParseUtils.symbol(sym.SCOL);
    }
    ":"/{whitespace}*{font} {return ParseUtils.symbol(sym.COL);}
    "}" {
        yybegin(YYINITIAL);
        return ParseUtils.symbol(sym.RBRACE);
    }
    ","/{whitespace}*{font} {return ParseUtils.symbol(sym.COM);}

    {font}/{whitespace}*[,;}] {return ParseUtils.symbol(sym.FONT, yytext());}
    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in font family at " + yyline + ":" + yycolumn);}
}

<ATTRIBUTE_VALUE> {
    ";" {
        yybegin(ATTRIBUTE);
        return ParseUtils.symbol(sym.SCOL);
    }
    "}" {
        yybegin(YYINITIAL);
        return ParseUtils.symbol(sym.RBRACE);
    }
    "|" {
        yybegin(CONDITIONAL_CONDITION_TYPE);
        return ParseUtils.symbol(sym.START_CON);
    }
    {url} {return ParseUtils.symbol(sym.URL, ParseUtils.parseUrl(yytext()));}
    {attribute_value} {return ParseUtils.symbol(sym.ATTRIBUTE_VALUE, yytext());}
    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in attribute value at " + yyline + ":" + yycolumn);}
}

<CONDITIONAL_CONDITION_TYPE> {
    ":" {
        yybegin(CONDITIONAL_CONDITION_VALUE);
        return ParseUtils.symbol(sym.COL);
    }

    {orientation}/{whitespace}*:{whitespace}*{orientation_type} {return ParseUtils.symbol(sym.ORIENTATION);}

    {size_condition}/{whitespace}*:{whitespace}*{size_mesurement} {return ParseUtils.symbol(sym.SIZE_CON, yytext());}

    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in conditional type at " + yyline + ":" + yycolumn);}
}

<CONDITIONAL_CONDITION_VALUE> {
    "?" {
        yybegin(CONDITIONAL_VALUE1);
        return ParseUtils.symbol(sym.QMK);
    }
    {orientation_type} {return ParseUtils.symbol(sym.ORIENTATION_TYPE, yytext());}
    {size_mesurement} {return ParseUtils.symbol(sym.SIZE, yytext());}

    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in conditional type attribute at " + yyline + ":" + yycolumn);}
}

<CONDITIONAL_VALUE1> {
    ":" {
        yybegin(CONDITIONAL_VALUE2);
        return ParseUtils.symbol(sym.OR);
    }
    {attribute_value} {return ParseUtils.symbol(sym.ATTRIBUTE_VALUE, yytext());}

    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in conditional attribute at " + yyline + ":" + yycolumn);}
}
<CONDITIONAL_VALUE2> {
    "|" {
        yybegin(ATTRIBUTE_VALUE);
        return ParseUtils.symbol(sym.END_CON);
    }
    {attribute_value} {return ParseUtils.symbol(sym.ATTRIBUTE_VALUE, yytext());}

    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in conditional attribute at " + yyline + ":" + yycolumn);}
}
