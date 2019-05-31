**ComPosSystem-spring**

Проект доступен по [ссылке](https://aqueous-anchorage-59043.herokuapp.com/login)

API:
* для получения всех категорий и вложенных продуктов 
https://aqueous-anchorage-59043.herokuapp.com/api/category/getAll 

Пример:
```html
[
    {
        "id": 3,
        "name": "Напитки",
        "products": [
            {
                "id": 5,
                "name": "Кола",
                "wholePrice": 0,
                "markup": 34,
                "retailPrice": 0,
                "barcode": 6738448928429
            },
            {
                "id": 6,
                "name": "Фанта",
                "wholePrice": 0,
                "markup": 47,
                "retailPrice": 0,
                "barcode": 3782454738953
            }
        ]
    },
    {
        "id": 4,
        "name": "Салаты",
        "products": [
            {
                "id": 7,
                "name": "Цезарь",
                "wholePrice": 0,
                "markup": 76,
                "retailPrice": 0,
                "barcode": 7435838534589
            }
        ]
    }
]
```


* для для отправки данных о таварах в чеке на сервер 
https://aqueous-anchorage-59043.herokuapp.com/api/check/save

Пример(тело запроса):
```html
[
	{
		"name": "Кола",
		"count": 5
	},
	{
		"name": "Фанта",
		"count": 10
	},
	{
		"name": "Чипсы Lay's",
		"count": 7
	}
]
```
