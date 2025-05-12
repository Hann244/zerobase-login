INSERT INTO USERS (ID, EMAIL, PASSWORD, PHONE, REG_DATE, UPDATE_DATE, USER_NAME, STATUS, LOCK_YN)
VALUES (1, 'test@naver.com', '1111', '010-1111-2222', '2021-02-01 00:49:43.000000', null, '박규태', 1, 0)
     , (2, 'test1@gmail.com', '2222', '010-3333-4444', '2021-02-19 00:50:11.000000', null, '정혜경', 1, 0)
     , (3, 'test2@gmail.com', '3333', '010-5555-6666', '2021-02-19 23:27:07.000000', null, '박하은', 1, 0)
     , (4, 'test3@gmail.com', '4444', '010-7777-9999', '2021-02-02 00:27:51.000000', null, '박하영', 2, 0);


INSERT INTO NOTICE (ID, CONTENTS, DELETED_DATE, DELETED, HITS, LIKES, REG_DATE, TITLE, UPDATE_DATE, USER_ID)
VALUES (2, '내용2', null, false, 0, 0, '2021-02-01 01:12:37.000000', '제목2', null, 1)
     , (1, '내용1', null, false, 0, 0, '2021-02-01 01:12:20.000000', '제목1', null, 1)
     , (3, '내용3', null, false, 0, 0, '2021-02-01 01:13:07.000000', '제목3', null, 2);
