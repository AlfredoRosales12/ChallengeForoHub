create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(100) not null unique,
    fecha_de_creacion datetime not null,
    status varchar(100) not null,
    usuario_id bigint not null,
    curso varchar(100) not null,

    primary key(id),

    CONSTRAINT fk_topicos_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE


);