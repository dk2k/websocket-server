
    alter table DATA 
        drop 
        foreign key FK_1lt8t7dpnmy0ibcgvwc9op9af;

    alter table MESSAGE 
        drop 
        foreign key FK_oni6xrrclkrilh8axijopgu2;

    alter table MESSAGE 
        drop 
        foreign key FK_q8kl8hj24syk0b2m671y4kn8x;

    alter table TOKENS 
        drop 
        foreign key FK_n6n8n5mwvvstdmp0kgfq25l2h;

    alter table TOKENS 
        drop 
        foreign key FK_84kwyu3stqxcla378ja2mcv0v;

    drop table if exists DATA;

    drop table if exists MESSAGE;

    drop table if exists SESSION;

    drop table if exists TOKENS;

    drop table if exists USER;

    create table DATA (
        MESSAGE_ID bigint not null,
        VALUE_ varchar(255),
        KEY_ varchar(255) not null,
        primary key (MESSAGE_ID, KEY_)
    );

    create table MESSAGE (
        MESSAGE_ID bigint not null auto_increment,
        SEQUENCE_ID varchar(100) not null,
        TYPE varchar(255),
        SESSION_ID bigint not null,
        primary key (MESSAGE_ID)
    );

    create table SESSION (
        ID bigint not null auto_increment,
        SESSION_ID varchar(100) not null,
        DATE timestamp default now() not null,
        primary key (ID)
    );

    create table TOKENS (
        TOKEN_ID bigint not null auto_increment,
        TOKEN varchar(100) not null,
        DATE timestamp default now() not null,
        USER_ID bigint not null,
        VALID char(1) not null,
        primary key (TOKEN_ID)
    );

    create table USER (
        USER_ID bigint not null auto_increment,
        USERNAME varchar(100) not null,
        PASSWORD varchar(20) not null,
        primary key (USER_ID)
    );

    alter table SESSION 
        add constraint UK_6p2qpgn77abcyf0fimxhrwjri unique (SESSION_ID);

    alter table TOKENS 
        add constraint UK_ifivne4ca8hmfltfbxyg2gea0 unique (TOKEN);

    alter table USER 
        add constraint UK_lb5yrvw2c22im784wwrpwuq06 unique (USERNAME);

    alter table DATA 
        add constraint FK_1lt8t7dpnmy0ibcgvwc9op9af 
        foreign key (MESSAGE_ID) 
        references MESSAGE (MESSAGE_ID);

    alter table MESSAGE 
        add constraint FK_oni6xrrclkrilh8axijopgu2 
        foreign key (SESSION_ID) 
        references SESSION (ID);

    alter table MESSAGE 
        add constraint FK_q8kl8hj24syk0b2m671y4kn8x 
        foreign key (MESSAGE_ID) 
        references SESSION (ID);

    alter table TOKENS 
        add constraint FK_n6n8n5mwvvstdmp0kgfq25l2h 
        foreign key (USER_ID) 
        references USER (USER_ID);

    alter table TOKENS 
        add constraint FK_84kwyu3stqxcla378ja2mcv0v 
        foreign key (TOKEN_ID) 
        references USER (USER_ID);
