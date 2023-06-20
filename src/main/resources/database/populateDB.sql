INSERT INTO country VALUES
(nextval('country_id_seq'), current_timestamp, current_timestamp, 'Spain', '47.3245', '7.42642'),
(nextval('country_id_seq'), current_timestamp, current_timestamp, 'Italy', '41.90278', '12.49637'),
(nextval('country_id_seq'), current_timestamp, current_timestamp, 'Greece', '37.5846', '23.4258');

INSERT INTO fish VALUES
(nextval('fish_id_seq'), current_timestamp, current_timestamp, 'Cod', 100),
(nextval('fish_id_seq'), current_timestamp, current_timestamp, 'Trout', 500),
(nextval('fish_id_seq'), current_timestamp, current_timestamp, 'Sprat', 10);