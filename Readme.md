# 3 labaratorinis darbas. Parseris CSS plėtiniui. 

## Padarė emkl9266

## Apie darbą

Skaneris skirtas CSS plėtiniui, kuris leidžia:
- blokui skirtą `!important`
- naudoti sąlyginį sakinį apibrėžiant atributus bloke
- klasių atributų paveldėjimą


## Kaip MIF kompiuteriui 

### Apie programą

Programos pagrindinėje direktorijoje saugomi 3 pavyzdiniai failai (`input_bad.txt`, `input_good.txt`, `input_sample.txt`). Programa sugeneruos 3 `{failo_pavadiminas}__out_good.md` ir 3 `{failo_pavadiminas}__out_bad.txt` atskirtus failus.

`{failo_pavadiminas}__out_good.md` faile saugomos leksemos, kurias pavyko nuskenuoti iš pavyzdinio failo

`{failo_pavadiminas}__out_bad.md` faile saugomos sutiktos leksemos, kurių nepavyko nuskenuoti

### Paleidimas

1. Paleisti `Go.cmd` failą.

## Kaip studentui

### Apie programą

Programa skenuoja įvesties failą `{input}` ir saugo leksemas, kurias pavyko nuskenuoti, viename išvesties faile `{output_good}`, o kitame išvesties faile saugo klaidas `{output_bad}`.

### Paleidimas

1. Pridėti `mvn` prie `path`.
2. Paleisti komandą `mvn compile`, kad sugeneruoti klases.
4. Paleisti žemiau esančią komandą vietoj `{input}`, `{output_good}`, `{output_bad}` įrašius failų pavadinimus.
```
mvn exec:java -D exec.mainClass="Main" -D exec.args="{input} {output_good} {output_bad}"
```

