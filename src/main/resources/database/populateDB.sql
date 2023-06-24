INSERT INTO country VALUES
(nextval('id_seq'), current_timestamp, current_timestamp, 'Spain', '47.3245', '7.42642'),
(nextval('id_seq'), current_timestamp, current_timestamp, 'Italy', '41.90278', '12.49637'),
(nextval('id_seq'), current_timestamp, current_timestamp, 'Greece', '37.5846', '23.4258');

INSERT INTO fish VALUES
(nextval('id_seq'), current_timestamp, current_timestamp, 'Cod', 100),
(nextval('id_seq'), current_timestamp, current_timestamp, 'Trout', 500),
(nextval('id_seq'), current_timestamp, current_timestamp, 'Sprat', 10);

INSERT INTO jetty VALUES
(nextval('id_seq'), current_timestamp, current_timestamp, 'First', 3, 10),
(nextval('id_seq'), current_timestamp, current_timestamp, 'Second', 1, 20);