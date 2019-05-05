CREATE TABLE company
  (
    id VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    PRIMARY KEY (id)
  );
  
  CREATE TABLE catalog
  (
    id VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    company_id VARCHAR NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE
  );
  
  CREATE TABLE item
  (
    id VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    price INT NOT NULL,
    availability INT NOT NULL,
    catalog_id VARCHAR NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (catalog_id) REFERENCES catalog(id) ON DELETE CASCADE
  );
  
  CREATE TABLE customer
  (
    id VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    address VARCHAR NOT NULL,
    phone VARCHAR NOT NULL,
    PRIMARY KEY (id)
  );
  
  CREATE TABLE credit_card
  (
    id VARCHAR NOT NULL,
    card_number NUMERIC NOT NULL,
    expiration_date DATE NOT NULL,
    cvc INT NOT NULL,
    customer_id VARCHAR NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
  );
  
  CREATE TABLE promo_code
  (
    id VARCHAR NOT NULL,
    expiration_date DATE NOT NULL,
    discount INT NOT NULL,
    item_id VARCHAR,
    PRIMARY KEY (id),
    FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE SET NULL
  );
  
  CREATE TABLE shopping_cart
  (
    id VARCHAR NOT NULL,
    customer_id VARCHAR NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
  );
  
  CREATE TABLE purchase
  (
    id VARCHAR NOT NULL,
    create_date DATE NOT NULL,
    status VARCHAR NOT NULL,
    amount INT NOT NULL,
    item_id VARCHAR,
    promo_id VARCHAR,
    cart_id VARCHAR NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE SET NULL,
    FOREIGN KEY (promo_id) REFERENCES promo_code(id) ON DELETE SET NULL,
    FOREIGN KEY (cart_id) REFERENCES shopping_cart(id) ON DELETE CASCADE
  );