CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    genero VARCHAR(255),
    edad INT,
    identificacion VARCHAR(255),
    direccion VARCHAR(255),
    telefono VARCHAR(255),
    cliente_id VARCHAR(255),
    contrasena VARCHAR(255),
    estado BOOLEAN
);

CREATE TABLE cuenta (
    numero_cuenta VARCHAR(255) PRIMARY KEY,
    tipo VARCHAR(255),
    saldo_inicial DOUBLE,
    estado BOOLEAN,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE movimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    tipo_movimiento VARCHAR(255),
    valor DOUBLE,
    saldo DOUBLE,
    cuenta_id VARCHAR(255),
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(numero_cuenta)
);
