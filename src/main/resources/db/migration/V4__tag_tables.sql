create table tags
(
    id   int auto_increment
        primary key,
    name varchar(255) not null
);

create table user_tags
(
    users_id bigint not null,
    tag_id   int    not null,
    constraint user_tags_pks
        primary key (users_id, tag_id),
    constraint user_tags_tags_id_fk
        foreign key (tag_id) references tags (id),
    constraint user_tags_users_id_fk
        foreign key (users_id) references users (id)
            on delete cascade
);