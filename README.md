## UrlShortCut 

Сервис обеспечивает безопасность пользователей при переходе по ссылке, предоставляя им возможность заменить её на сокращенную.
Сервис работает через REST API. 

При разработке использовались следующие технологии:

- Java 11
- Spring Boot
- Spring Data JPA
- Spring Security
- JSON Web Token
- PostgreSQL
- Maven

***

Вначале владельцу сайта необходимо зарегистрироваться

POST: 
```
/registration
```
![](images/1.png)

Затем, необходимо добавить в заголовок JWT с помощью полученных при регистрации данных.
POST: 
```
/login
```
![](images/2.png)

Теперь пользователю доступен функционал сокращения ссылки.
POST: 
```
/convert
```
![](images/3.png)

Теперь по полученному ключу можно получить полную ссылку. Для этого не нужно быть зарегестрированным в системе.
GET: 
```
/r/**
```
![](images/4.png)

Также пользователи, указавшие JWT в заголовке, могут получить статистику, содержащию количество переходов.
GET: 
```
/statistic
```
![](images/5.png)

