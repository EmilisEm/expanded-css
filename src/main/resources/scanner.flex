package scanner;

import java_cup.runtime.Symbol;
import scanner.ScanUtils;
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
    "," {return ScanUtils.symbol(sym.COM);}
    "{" {return ScanUtils.symbol(sym.LBRACE);}
    "}" {return ScanUtils.symbol(sym.RBRACE);}

    {media} {return ScanUtils.symbol(sym.MEDIA, ScanUtils.parseMedia(yytext()));}
    {selector} {
            yybegin(SELECTOR);
            return ScanUtils.getSelector(yytext());

        }
    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token at " + yyline + ":" + yycolumn);}
}

<SELECTOR> {
    "!important" {return ScanUtils.symbol(sym.IMPORTANT);}
    ">"/{selector} {return ScanUtils.symbol(sym.RAROW);}
    "(" {
            yybegin(INHERATANCE);
            return ScanUtils.symbol(sym.START_INH);
        }
    "{"/{whitespace}*({attribute}|}) {
            yybegin(ATTRIBUTE);
            return ScanUtils.symbol(sym.LBRACE);
        }
    {gap}/{selector} {return ScanUtils.symbol(sym.CHILD_SELECTOR);}
    {selector} {return ScanUtils.getSelector(yytext());}
    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in selector at " + yyline + ":" + yycolumn);}
}

<INHERATANCE> {
    "," {return ScanUtils.symbol(sym.COM);}
    ")" {
            yybegin(SELECTOR);
            return ScanUtils.symbol(sym.END_INH);
        }
    {selector}/[,)] {return ScanUtils.getSelector(yytext());}
    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in inheratance at " + yyline + ":" + yycolumn);}
}

<ATTRIBUTE> {
    ":" {
            yybegin(ATTRIBUTE_VALUE);
            return ScanUtils.symbol(sym.COL);
        }
    "}" {
            yybegin(YYINITIAL);
            return ScanUtils.symbol(sym.RBRACE);
        }
    {font_family}/{whitespace}*:{whitespace}*{font} {
            yybegin(FONT_FAMILY);
            return ScanUtils.symbol(sym.FONT_FAMILY);
        }
    {attribute}/{whitespace}*: {return ScanUtils.symbol(sym.ATTRIBUTE, yytext());}
    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in attribute at " + yyline + ":" + yycolumn);}
}

<FONT_FAMILY> {
    ";" {
            yybegin(ATTRIBUTE);
            return ScanUtils.symbol(sym.SCOL);
        }
    ":"/{whitespace}*{font} {return ScanUtils.symbol(sym.COL);}
    "}" {
            yybegin(YYINITIAL);
            return ScanUtils.symbol(sym.RBRACE);
        }
    ","/{whitespace}*{font} {return ScanUtils.symbol(sym.COM);}

    {font}/{whitespace}*[,;}] {return ScanUtils.symbol(sym.FONT, yytext());}
    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in font family at " + yyline + ":" + yycolumn);}
}

<ATTRIBUTE_VALUE> {
    ";" {
            yybegin(ATTRIBUTE);
            return ScanUtils.symbol(sym.SCOL);
        }
    "}" {
            yybegin(YYINITIAL);
            return ScanUtils.symbol(sym.RBRACE);
        }
    "|" {
            yybegin(CONDITIONAL_CONDITION_TYPE);
            return ScanUtils.symbol(sym.START_CON);
        }
    {url} {return ScanUtils.symbol(sym.URL, ScanUtils.parseUrl(yytext()));}
    {attribute_value} {return ScanUtils.symbol(sym.ATTRIBUTE_VALUE, yytext());}
    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in attribute value at " + yyline + ":" + yycolumn);}
}

<CONDITIONAL_CONDITION_TYPE> {
    ":" {
            yybegin(CONDITIONAL_CONDITION_VALUE);
            return ScanUtils.symbol(sym.COL);
        }

    {orientation}/{whitespace}*:{whitespace}*{orientation_type} {return ScanUtils.symbol(sym.ORIENTATION);}

    {size_condition}/{whitespace}*:{whitespace}*{size_mesurement} {return ScanUtils.symbol(sym.SIZE_CON, yytext());}

    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in conditional type at " + yyline + ":" + yycolumn);}
}

<CONDITIONAL_CONDITION_VALUE> {
    "?" {
            yybegin(CONDITIONAL_VALUE1);
            return ScanUtils.symbol(sym.QMK);
        }
    {orientation_type} {return ScanUtils.symbol(sym.ORIENTATION_TYPE, yytext());}
    {size_mesurement} {return ScanUtils.symbol(sym.SIZE, yytext());}

    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in conditional type attribute at " + yyline + ":" + yycolumn);}
}

<CONDITIONAL_VALUE1> {
    ":" {
            yybegin(CONDITIONAL_VALUE2);
            return ScanUtils.symbol(sym.OR);
        }
    {attribute_value} {return ScanUtils.symbol(sym.ATTRIBUTE_VALUE, yytext());}

    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in conditional attribute at " + yyline + ":" + yycolumn);}
}
<CONDITIONAL_VALUE2> {
    "|" {
            yybegin(ATTRIBUTE_VALUE);
            return ScanUtils.symbol(sym.END_CON);
        }
    {attribute_value} {return ScanUtils.symbol(sym.ATTRIBUTE_VALUE, yytext());}

    {whitespace} { }
    . {throw new IllegalArgumentException("Unexpected token in conditional attribute at " + yyline + ":" + yycolumn);}
}
