INSERT INTO `hotel_epam`.`user` (`first_name`, `role`, `last_name`, `login`, `password`) VALUES ('aleksey','ADMIN', 'slovyagin', 'aleksey', '12345678');
INSERT INTO `hotel_epam`.`user` (`first_name`, `role`, `last_name`, `login`, `password`) VALUES ('artem','USER', 'kunatz', 'arteem', '12345678');

INSERT INTO `hotel_epam`.`room_type` (`description`, `name`) VALUES ('big room', 'luxe');
INSERT INTO `hotel_epam`.`room_type` (`description`, `name`) VALUES ('small room', 'ord');

INSERT INTO `hotel_epam`.`room` (`cost_night`, `number`, `number_place`, `room_type_id`) VALUES ('1000', '11', '1', '2');
INSERT INTO `hotel_epam`.`room` (`cost_night`, `number`, `number_place`, `room_type_id`) VALUES ('2000', '2', '3', '2');

INSERT INTO `hotel_epam`.`room_request` (`arrival_date`, `capacity`, `departure_date`, `is_done`, `room_type_id`, `user_id`) VALUES ('2008-07-23', '2', '2008-07-23', b'1', '2', '2');

