DROP TABLE Sale;

create table Sale(
    id UUID default random_uuid() primary key,
    invoice INT,
    sale_date DATE,
    ci_seller VARCHAR(50),
    ci_buyer VARCHAR(50),
    total_price DECIMAL
    );

insert into Sale (invoice,sale_date,ci_seller,ci_buyer,total_price)values(12345, NOW(), '11.111.111-1', '11.111.222-2', 10000);
insert into Sale (invoice,sale_date,ci_seller,ci_buyer,total_price)values(12346, NOW(), '22.222.222-2', '22.222.333-3', 13000);
insert into Sale (invoice,sale_date,ci_seller,ci_buyer,total_price)values(12347, NOW(), '33.333.333-3', '33.333.333-4', 17800);
insert into Sale (invoice,sale_date,ci_seller,ci_buyer,total_price)values(12348, NOW(), '44.444.444-4', '44.444.555-5', 18900);