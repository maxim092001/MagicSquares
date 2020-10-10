# MagicSquares. Тестовое задания для Cone Center.

Задача
---
Есть 12 квадратов с произвольными числами от 0 до 9 в углах.
* **Расстановка:**

![Example](https://github.com/maxim092001/MagicSquares/blob/master/src/main/resources/images/example.png)

Квадраты надо поменять местами таким образом, чтобы сумма чисел четырех соприкасающихся углов была равна 10 (подкрашено зеленым), а двух или трех углов - не более 10 (подкрашено желтым).

* **Раскраска:**

![Colors](https://github.com/maxim092001/MagicSquares/blob/master/src/main/resources/images/colors.png)

На входе текстовый файл, одна строка – четыре числа – значения углов одного квадрата. 

* **Порядок строк и чисел в строке следующий:**

![Order](https://github.com/maxim092001/MagicSquares/blob/master/src/main/resources/images/order.png)

Требуется вывести все возможные уникальные ответы разделенные пустой строкой.

Решение
---

Рассмотрим все возможные четверки квадратов, у которых середина будет иметь сумму ровно 10. 

![Green square](https://github.com/maxim092001/MagicSquares/blob/master/src/main/resources/images/green_square.png)

Занесем все **уникальные** четверки в ```List```.

Далее будем пытаться *собрать* доску из оставшихся квадратов для каждой четверки.

Алгоритм:
* Добавим прямоугольник из двух квадратов сверху, проверив сумму на равенство 10. Все такие **уникальные** фигуры опять занесем в ```List```

![Upper](https://github.com/maxim092001/MagicSquares/blob/master/src/main/resources/images/upper.png)

* Добавим прямоугольник из двух квадратов справа. Аналогично.

![Right](https://github.com/maxim092001/MagicSquares/blob/master/src/main/resources/images/right.png)

* Добавим прямоугольник из двух квадратов снизу. Аналогично.

![Lower](https://github.com/maxim092001/MagicSquares/blob/master/src/main/resources/images/lower.png)

* Добавим прямоугольник из двух квадратов слева. Аналогично.

![Left](https://github.com/maxim092001/MagicSquares/blob/master/src/main/resources/images/left.png)

Таким образом в конце мы получим ```List``` из **уникальных** досок удовлетворяющих требованию *зеленый сумм*. 

Для каждой доски проверим требование *желтых сумм*.

![Yellow](https://github.com/maxim092001/MagicSquares/blob/master/src/main/resources/images/yellow.png)

Результат
---

Если после такой фильтрации наш ```List``` не пустой, то выведем ответ, иначе соотвествующее сообщение: "*There is no satisfying permutation of the given cells.*"
