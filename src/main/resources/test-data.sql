INSERT INTO BRANDS (BRAND_NAME) VALUES ('Zara');
INSERT INTO BRANDS (BRAND_NAME) VALUES ('Pull&Bear');

INSERT INTO PRODUCTS (PRODUCT_ID, PRODUCT_NAME) VALUES ('35456', 'camisa lino');

INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE,PRODUCT_ID,PRIORITY,PRICE,CURRENCY) 
VALUES ('1', '2020-06-14 00:00:00', '2020-12-31 23:59:59','35455','0','35.50','EUR');
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE,PRODUCT_ID,PRIORITY,PRICE,CURRENCY) 
VALUES ('1', '2020-06-14 15:00:00', '2020-06-14 18:30:00','35455','1','25.45','EUR');
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE,PRODUCT_ID,PRIORITY,PRICE,CURRENCY) 
VALUES ('1', '2020-06-15 00:00:00', '2020-06-15 11:00:00','35455','1','30.50','EUR');
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE,PRODUCT_ID,PRIORITY,PRICE,CURRENCY) 
VALUES ('1', '2020-06-15 16:00:00', '2020-12-31 23:59:59','35455','1','38.95','EUR');