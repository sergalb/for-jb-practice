1 задание - поссмотрим на все пары соседних имен, для каждой найдем первую букву, где они различаются. В искомой перестановке алфавита найденная буква из первого слова должна стоять раньше буквы из второго слова. Проверим, что такой порядок не нарушает текущих найденных условий, и добавим это условие. Когда все условия найденны (в случае, если они не протеворечат друг другу) отсортируем алфавит с этими условиями и получим искомую перестановку. Ассимптотика - суммарно за время работы программы будет уствновлено не больше 25 * 26 отношений между буквами + каждая из N итераций стоит длину кратчайшего слова.
2 задание - просто кладем имена в map с значением текущего максимального индекса имени.
