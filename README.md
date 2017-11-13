XY-INC
====
##### Desenvolvido em java usando o padrao REST através da implementação jersey
Para buildar a aplicação:
- Ter o gradle instalado
- Ter jdk instalado
- Ter um banco de dados mysql

Executar o gradle: ``` gradle build --x test ```
Para o deploy basta colocar o arquivo gerado em `war/xy-inc.war` no diretorio de deploy do sorvidor de aplicação(tomcat, glassfish, ect..)

Para criar o banco de dados basta executar o comando 
```sql 
CREATE DATABASE `pois_db`
```
### Utilizando o serviço
Para listar todos os POI's basta dar um `GET` para `xy-inc/resources/pois`
Ele retornara um json no formato: 
```javascript
[{
    name: "*nome*",
    x: 0,
    y: 0
}]
```
Para criar um POI basta mandar um `POST` para `xy-inx/resources/pois` com o json do mesmo formato acima sem as []

Para os POI's a uma distancia "d" do ponto (x, y) basta dar um `GET` para `/xy-inc/resources/pois/x/y/d`