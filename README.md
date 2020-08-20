# customer-api

An API in Java and Spring Framework for customer registration.

## How should the API work?

The API must create, update, delete and list our customers.
The API has the following endpoints:

`POST / customers`: register a customer.

** Body: **

<code>
 {
    "cpf": "cpf",
    "fullName": "John Peter",
    "customerAddress": {
"id": 6,
      "street": "Street",
      "number": "Number",
      "city": "City",
      "state": "State",
      "zipCode": "zip code",
      "country": "Country"
    }
  }
</code>

**Onde:**

`cpf`: client id;
`fullName`: full name of the customer.
`customerAddress`: full address of the customer.

You must return with body with one of the following codes:

- 201: if successful.

`PUT / customers / {id}`: updates a customer.

** Body: **

<code>
{
    "cpf": "cpf",
    "fullName": "John Peter",
    "customerAddress": {
"id": 6,
      "street": "Street",
      "number": "Number",
      "city": "City",
      "state": "State",
      "zipCode": "zip code",
      "country": "Country"
    }
  }
</code>

The object to be modified must be sent. Void return.

The response must contain the following codes:

- 200: in case of success.

`GET / customers`: returns all customers.

A list of customers must be returned.

<code>
{
    "cpf": "cpf",
    "fullName": "John Peter",
    "customerAddress": {
"id": 6,
      "street": "Street",
      "number": "Number",
      "city": "City",
      "state": "State",
      "zipCode": "zip code",
      "country": "Country"
    }
  },
{
    "cpf": "cpf",
    "fullName": "John Peter",
    "customerAddress": {
"id": 6,
      "street": "Street",
      "number": "Number",
      "city": "City",
      "state": "State",
      "zipCode": "zip code",
      "country": "Country"
    }
  }
</code>

The response must contain the following codes:

- 200: if there are registered customers
- 404: if there are no customers created.

`DELETE / customers / {id}`: remove a client by id.
