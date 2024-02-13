CREATE TABLE `role`
(
    id          BINARY(16)   NOT NULL,
    created_at  datetime     NULL,
    modified_at datetime     NULL,
    `role`      VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE session
(
    id             BINARY(16)   NOT NULL,
    created_at     datetime     NULL,
    modified_at    datetime     NULL,
    token          VARCHAR(255) NULL,
    session_status TINYINT     NULL,
    user_id        BINARY(16)   NULL,
    expire_at      datetime     NULL,
    CONSTRAINT pk_session PRIMARY KEY (id)
);

CREATE TABLE user
(
    id          BINARY(16)   NOT NULL,
    created_at  datetime     NULL,
    modified_at datetime     NULL,
    email       VARCHAR(255) NULL,
    password    VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id  BINARY(16) NOT NULL,
    roles_id BINARY(16) NOT NULL
);

ALTER TABLE session
    ADD CONSTRAINT FK_SESSION_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);