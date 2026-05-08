INSERT INTO category (id, name, description) VALUES (1, 'Keyboard', 'Computer Keyboards');
INSERT INTO category (id, name, description) VALUES (2, 'Screen', 'Computer Monitors/Screens');
INSERT INTO category (id, name, description) VALUES (3, 'Mice', 'Computer Mice');
INSERT INTO category (id, name, description) VALUES (4, 'Accessories', 'Computer Accessories');

INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (1, 'Mechanical Gaming Keyboard', 'RGB Mechanical Keyboard with Blue Switches', 50, 89.99, 1);
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (2, 'Wireless Office Keyboard', 'Slim and quiet wireless keyboard', 100, 35.50, 1);

INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (3, '27-inch Gaming Monitor', '144Hz Refresh Rate, 1ms Response Time', 30, 249.99, 2);
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (4, 'UltraWide Productivity Screen', '34-inch 21:9 Aspect Ratio', 15, 450.00, 2);

INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (5, 'Ergonomic Wireless Mouse', 'Comfortable grip for long working hours', 80, 25.00, 3);
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (6, 'RGB Gaming Mouse', 'High precision sensor with customizable buttons', 60, 45.99, 3);

INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (7, 'Extended Mouse Pad', 'Large surface area for keyboard and mouse', 150, 15.99, 4);
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (8, 'USB-C Multiport Adapter', '7-in-1 Hub for laptops', 200, 39.00, 4);
