Chlopaki taki zalozenia sa:
* 0 zwykle pole biale
* 1- Czarne z 1
* 2- Czarne z 2
* 3- Czarne z 3
* 4- Czarne z 4
* 5- Czarne z 0
* 6- Czarne bez niczego
        
Mapy najlepiej zrobic tablica int[][] z cyferkami bo latwo je bedzie zaladowac i tak mozemy zapisywac do csv

Koncepcja solvera zintegrowanego z generatorem:
- generujemy losową strukturę z samymi czarnymi i białymi polami (bez numerów)
- solvujemy tą mapę (...)
- zmieniamy czarne bloki przy żarówkach na bloki z numerami odpowiadającymi ilości żarówek
w rozwiązaniu
- zapisujemy do pliku csv w postaci tablicy 2/x gdzie x to liczba żarówek (przy uruchomieniu
solvera pozostaje tylko odczyt) 