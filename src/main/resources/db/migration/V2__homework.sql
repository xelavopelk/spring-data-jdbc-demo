create table review (
                        id bigserial primary key,
                        user_name varchar(255),
                        text varchar(255),
                        book_id bigint,
                        created_at timestamp,
                        score smallint
);

insert into review (user_name, text, book_id, created_at, score)
values
('John', 'some words', 1, now(), 5),
('John 2', 'some words2', 1, now(), 7),
('Paul', 'some words3', 2, now(), 2);
