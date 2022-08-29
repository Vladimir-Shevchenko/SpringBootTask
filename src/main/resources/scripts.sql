CREATE TABLE     credit   (
                              idClient int NOT NULL,   -- идентификатор клиента
                              idCredit int  NOT NULL,   --идентификатор кредита
                              amtCredit   decimal (15,2) NOT NULL, --сумма задолженности
                              dateStart date  NOT NULL, -- дата старта
                              stateCredit char(1), -- состояние кредита (O - открыт, C - закрыт)
                              primary key ( idClient , idCredit  )
)

CREATE TABLE client (
                        idClient int NOT NULL, -- идентификатор клиента
                        dateCurr timestamp NOT NULL,  -- текущая дата на момент записи  данных по заявке в базу
                        phone varchar(45) DEFAULT NULL, -- номер телефона
                        mail varchar(45) DEFAULT NULL, -- email
                        address varchar(45) DEFAULT NULL, --адресс клиента
                        monthSalary decimal (15,2) DEFAULT NULL, -- сумма дохода клиента
                        CurrSalary char(3)-- валюта дохода клиента
                            decision varchar(45) DEFAULT NULL, --решение по кредита
                        limitItog  decimal (15,2) DEFAULT NULL, -- сумма лимита по кредиту
                        primary key ( idClient , dateCurr )
)
