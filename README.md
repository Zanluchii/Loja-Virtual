# Loja Virtual - Controle de Estoque

Este é um aplicativo Android desenvolvido para modernizar a organização de uma pequena loja de produtos eletrônicos. O sistema substitui o controle manual por um banco de dados digital, permitindo o gerenciamento eficiente de produtos em estoque.

## 🚀 Funcionalidades

### 1. Tela de Cadastro de Produto
Permite o registro de novos itens no estoque com as seguintes informações:
- **Nome do Produto**: Descrição textual do item.
- **Código do Produto**: Identificador alfanumérico único.
- **Preço**: Valor em reais (R$).
- **Quantidade**: Total disponível em estoque.

**Validações Implementadas:**
- Todos os campos são obrigatórios.
- O campo **Preço** aceita apenas números positivos com até duas casas decimais.
- O campo **Quantidade** aceita apenas números inteiros positivos.

### 2. Tela de Listagem de Produtos
Exibe todos os produtos cadastrados em uma lista organizada:
- Apresenta o Nome, Código e Preço de cada item.
- Utiliza `RecyclerView` e `CardView` para uma interface moderna e fluida.
- Possui navegação facilitada para retornar à tela de cadastro.

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java
- **Banco de Dados**: Room Database (Persistência local)
- **Interface**: Material Design (Componentes modernos como TextInputLayout, CardView e RecyclerView)
- **Arquitetura**: DAO (Data Access Object) e Singleton para o banco de dados.

## 📂 Estrutura do Projeto (Room)

- `Product.java`: Entidade que define a tabela de produtos.
- `ProductDao.java`: Interface com os métodos de inserção e busca (SQL).
- `ProductDatabase.java`: Classe principal do Room que gerencia a instância do banco de dados.

## ⚙️ Como executar o projeto

1. Clone ou baixe este repositório.
2. Abra o projeto no **Android Studio**.
3. Aguarde a sincronização do Gradle.
4. Execute o aplicativo em um emulador ou dispositivo físico com Android API 24 ou superior.
