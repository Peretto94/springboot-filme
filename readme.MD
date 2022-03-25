Projeto Spring Boot

    - Spring Boot
    - API Rest
    - Spring Security
    - Spring Data
    - JPA
    - Java 11
    - Banco de Dados H2
    
    
Execução do Projeto:
    
    * Executar classe Run
        Após o 'start' da aplicação, a mesma irá buscar o arquivo movielist.csv,
            o qual se encontra no diretório: /resources/files/.
        Os dados do arquivo serão inseridos no banco de dados H2, na tabela Filme.
        Pode-se realizar uma requisição HTTP com verbo GET no seguinte endereço: http://localhost:8080/winner/result,
            o qual retornará um Json com dados dos Produtores ganhadores de prêmios.
        
        
Execução dos Testes:
    
    * Executar classe RunTests
        Após o 'start' da classe de testes, a mesma iniciará o método criado para busca dos Produtores ganhadores de prêmios.
        O teste usa as mesmas classes e métodos do projeto para busca dos dados em banco e disponibilização via API.


Acesso Banco de Dados:
    
    * Aplicação deve estar em execução.
    * No seu navegador acessar o endereço: http://localhost:8080/h2-console/
        * JDBC URL: jdbc:h2:file:./data/filme
        * User: sa
        * Password: password
