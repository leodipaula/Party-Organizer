# Party-Organizer
Esse projeto visa construir um SAAS que torna simples e intuitivo a organização de um evento/festa/amigo secreto, na qual os participantes poderão se organizarem exclusivamente através do WhatsApp.

### Possível nome:
Até então, o nome seria "CapyParty", porque capivara é um animal muito querido e tranquilo. Uma capivara que te ajuda a organizar seu evento, why not??

### Ideia de como será a implementação:
* A implementação não será simples. Como se utiliza do WhatsApp, e os participantes poderão participar de múltiplas festas, o número de requisições é esperado que seja alta. Lidar com isso não é simples, por isso, pode ser que tenhamos que utilizar o Kafkas.

* Na comunicação com o WhatsApp, será utilizado WebSocket para comunicação em tempo real. Redis para cache e evitar sobrecarga no banco de dados. Possívelmente precisaremos do WebFlux para evitar chamadas bloqueantes. Além disso, o consumo da API da Meta será via Open Feign ou Interface HTTP.

* O banco de dados será o PostgreSQL, pois possui funções que facilitam as querys.


### Funcionalidades:
* O organizador precisará entrar no site apenas para gerar o tipo de festa e cadastro de seu próprio número.

* O organizador poderá gerar convites QR codes ou links temporários. É temporário pois, caso o convite seja vazado, evita que o vento seja inundado de participantes indesejados.

* O organizador é também um participante, mas com privilégios para algumas funções sensíveis como: 
   - Banir um participante.
   - Mudar o preço de ingresso ou contribuíção.
   - Mudar o nome do evento, data, e demais informações acerca do evento.
   - Pode ser interessante o organizador poder delegar essas funções para outros participantes.

* Cada participante terá acesso a cadastrar ou alterar certas as seguintes funcionalidades:
    - Cadastrar uma bebida ou comida que levará, ou que o evento precisará, e quanto custou/custará.
    - Confirmar sua presença.
    - Listar os eventos que está particiando.
    - Listar participantes e quais já confirmaram presença.
    - Cadastrar um presente - a função desse presente será determinada em função do tema do evento.
    - Os participantes terão a oportunidade de cadastrar um outro participante que não tenha um WhatsApp.
    - Os participantes que não têm WhatsApp, terão as mesmas funcionalidades mediante o participante que o cadastrou.

