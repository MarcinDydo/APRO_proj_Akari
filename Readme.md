Chlopaki taki zalozenia sa:
* 0 zwykle pole biale
* 1- Czarne z 1
* 2- Czarne z 2
* 3- Czarne z 3
* 4- Czarne z 4
* 5- Czarne z 0
* 6- Czarne bez niczego
* 7 - lit
* 8 - bulb
        
Mapy najlepiej zrobic tablica int[][] z cyferkami bo latwo je bedzie zaladowac i tak mozemy zapisywac do csv

Koncepcja solvera zintegrowanego z generatorem:
- generujemy losową strukturę z samymi czarnymi i białymi polami (bez numerów)
- solvujemy tą mapę (...)
- zmieniamy czarne bloki przy żarówkach na bloki z numerami odpowiadającymi ilości żarówek
w rozwiązaniu
- zapisujemy do pliku csv w postaci tablicy 2/x gdzie x to liczba żarówek (przy uruchomieniu
solvera pozostaje tylko odczyt) 

tak mże wygladać plik csv, po # rozpoczyna sie miejsce w ktorym zaisane sa kordymaty dla żąrówek

```
7;7    (zapisujemy tak jak wczesniej ustalone, w postaci  mapy[][])
0;2;0;5;0;0;0
0;0;0;0;0;0;6
0;0;0;0;0;0;0
6;0;0;5;0;0;3
0;0;0;0;0;0;0
1;0;0;0;0;0;0
0;0;0;6;0;6;0
#   -znak especjalny aby odroznic 
1,3;4,5;1,5 (przyklad podawania kordynatów w ktorych miejscach owinny być żąróki
```
# Ziutki
Dydo - akari.maps.Generator
Dadura - akari.maps.Solver

Przekazujemy tablice intów na koncu
