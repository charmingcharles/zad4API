Stworzene przeze mnie API zwraca następujące arguemty:
* original-text - oryginalny tekst
* length - długość tekstu
* lowercase-count - zliczenie ilości wystąpień małej litery
* uppercase-count - zliczenie ilości wystąpień wielkiej litery
* special-signs-count - zliczenie ilości wystąpień znaków specjalnych
* numbers-count - zliczenie ilości wystąpień cyfr

Może to zrobić w 4 różnych formatach:
* tekst
* [XML](https://www.w3.org/XML/)
* [JSON](https://www.json.org/json-en.html)
* [CSV](https://pl.wikipedia.org/wiki/CSV_(format_pliku))

Wywołanie zwracające wynik w różnych formatach:
```
http://localhost:8100/anyresult?text=Karol&type=json
```
Parametry:
* text - tekst dla którego chcemy wykonać obliczenia
* type - typ danych w którym ma zwrócić wynik (dostępne 4)


Wywołanie przeprowadzające zamianę wyniku pomiędzy typami danych:
```
http://localhost:8100/convert?from=json&to=xml
```
Parametry:
* from - typ danych z którego mamy przeprowadzić zamianę
* to - typ danych do których mamy przeprowadzić zamianę