INSERT INTO user VALUES(90001, sysdate(), 'User1', 'test1111', '901010-11111111');
INSERT INTO user VALUES(90002, sysdate(), 'User2', 'test2222', '801010-11111111');
INSERT INTO user VALUES(90003, sysdate(), 'User3', 'test3333', '701010-11111111');

INSERT INTO post VALUES(10001, 'My first post', 90001);
INSERT INTO post VALUES(10002, 'My second post', 90001);