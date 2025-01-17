# Popcorn Time 🎥!

Aplicativo para buscar informações sobre filmes utilizando da OMDb API.

## Funcionalidades
- 🔍 Pesquisa de filmes por título.
- 📜 Exibe lista de resultados com informações básicas.
- 📊 Mostra detalhes completos, incluindo notas do IMDb.

## Tecnologias
- **Kotlin**
Feito com Kotlin 2.0!
- **Coroutines**
- **Retrofit**
- **Jetpack Compose**
- **Dagger**
- **Lottie**
- **Gson**
- **Okhttp**
- **Coil**
- **Data Binding**

## Arquitetura
- **Domain-Data-Presentation:**
Nesse app temos a estrutura domain-data-presentation. 
É arquitetura em camadas, que organiza o código pra deixar o app mais fácil de escalar, manter e testar. 
Aqui nesse app, ele não está separado por feature (de forma com que cada feature possua seu próprio data/domain/presentation, e não super classes) por se tratar de uma aplicação pequena.
Essa separação em camadas é bem útil para separarmos melhor as responsabilidades.
Na camada de Data, possuimos requisições de API (e seus respectivos objetos de response), repository (que serve de ponte entre API e domain) e demais configurações relacionados ao Retrofit.
Na camada de Domain, possuimos regras de negócio e demais manipulações de dados, inclusive a conversão de objeto de response para objeto de domain por meio de Mappers.
Na camada de Presentation, possuimos nossas ViewModels, XML, States e demais interações de interface.

- **Clean Arch:**
Aqui temos então a ideia de isolar as diferentes responsabilidades do app em camadas distintas.
Esse app segue a regra de "dependência da camada externa não pode ser propagada para a camada interna".
Segue-se o fluxo data -> domain -> presentation, onde no final temos uma View que só recebe ou observa dados.

## DI
- **Dagger:**
O Dagger, embora seja uma ferramenta poderosa, pode adicionar uma complexidade significativa ao projeto, especialmente devido à necessidade de gerenciar Factories manuais, a configuração extensa de Modules e as dificuldades para criar ViewModels que exigem tipos de instâncias especiais, como o SavedStateHandle.
Apesar do projeto já estar implementado com Dagger, recomendo fortemente que considere uma refatoração gradual para adotar o Hilt. O Hilt, sendo uma abstração do Dagger, pode coexistir com ele no mesmo projeto, permitindo uma migração mais simples e progressiva.

## Views
- **Jetpack Compose:**
Este projeto foi desenvolvido com a tela inicial utilizando Jetpack Compose, que demonstra a facilidade de coexistência entre Views tradicionais (XML) e Compose. 
Isso comprova que é possível refatorar gradualmente um projeto Android legado, incorporando Compose sem a necessidade de reescrever todo o código de UI de uma vez.
Ao adotar Compose de forma incremental, a equipe pode aproveitar as vantagens dessa nova abordagem moderna para UI, enquanto ainda mantém as partes do projeto que utilizam Views tradicionais.
- **XML com DataBinding:**
Usar Data Binding no XML oferece vantagens significativas em relação a manipular dados diretamente no Fragment. Com o Data Binding, a lógica de apresentação é desacoplada da lógica de negócios, deixando o código do Fragment mais limpo e fácil de manter.
Ele permite que você vincule dados e defina ações diretamente no XML, evitando manipulações manuais de UI e melhorando a legibilidade.
Com o Data Binding, você pode vincular diretamente variáveis de dados (como LiveData, StateFlow ou Observable) à UI, permitindo que a interface se atualize automaticamente sempre que os dados mudam.
- **Navigation Component:**
Nesse projeto foi utilizado o Navigation Component, para demonstrar que ele consegue ser utilizado em conjunto com uma tela Compose normalmente.
No entanto, trocar para o Navigation Compose é uma escolha ainda mais vantajosa, pois ele é otimizado para Jetpack Compose, proporcionando uma navegação totalmente declarativa.
