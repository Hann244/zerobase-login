INSERT INTO USERS (EMAIL, PASSWORD, PHONE, REG_DATE, UPDATE_DATE, USER_NAME, STATUS, LOCK_YN)
VALUES ( 'test@naver.com', '$2a$10$aVjtWrojTOJJStC5EFH6Z.nG/U4c0PXJke3pmYWElmS/M77dG3Ud2', '010-1111-2222', '2021-02-01 00:49:43.000000', null, '박규태', 1, 0)
     , ('test1@gmail.com', '2222', '010-3333-4444', '2021-02-19 00:50:11.000000', null, '정혜경', 1, 0)
     , ('test2@gmail.com', '3333', '010-5555-6666', '2021-02-19 23:27:07.000000', null, '박하은', 1, 0)
     , ('test3@gmail.com', '4444', '010-7777-9999', '2021-02-02 00:27:51.000000', null, '박하영', 2, 0);


INSERT INTO NOTICE (CONTENTS, DELETED_DATE, DELETED, HITS, LIKES, REG_DATE, TITLE, UPDATE_DATE, USER_ID)
VALUES ('내용2', null, false, 0, 0, '2021-02-01 01:12:37.000000', '제목2', null, 1)
     , ('내용1', null, false, 0, 0, '2021-02-01 01:12:20.000000', '제목1', null, 1)
     , ('내용3', null, false, 0, 0, '2021-02-01 01:13:07.000000', '제목3', null, 2)
     , ('내용4', null, false, 0, 0, '2021-02-01 01:13:10.000000', '제목4', null, 2)
     , ('내용5', null, false, 0, 0, '2021-02-01 01:13:12.000000', '제목5', null, 2)
     , ('내용6', null, false, 0, 0, '2021-02-01 23:31:23.000000', '제목6', null, 1)
     , ('내용7', null, false, 0, 0, '2021-02-01 23:31:26.000000', '제목7', null, 3)
     , ('내용8', null, false, 0, 0, '2021-02-01 23:31:32.000000', '제목8', null, 3)
     , ('내용9', null, false, 0, 0, '2021-02-01 23:31:35.000000', '제목9', null, 1)
     , ('내용10', null, false, 0, 0, '2021-02-01 23:31:38.000000', '제목10', null, 1);

INSERT INTO NOTICE_LIKE (NOTICE_ID, USER_ID)
VALUES (3, 1)
     , (4, 1)
     , (1, 1)
     , (3, 2)
     , (1, 4)
     , (2, 4);

INSERT INTO BOARD_TYPE (BOARD_NAME, REG_DATE, USING_YN)
VALUES ('게시판1', '2021-02-01 01:12:37.000000', 1)
     , ('게시판2', '2021-02-01 01:12:37.000000', 1);


INSERT INTO BOARD (BOARD_TYPE_ID, USER_ID, TITLE, CONTENTS, REG_DATE, TOP_YN)
VALUES (1, 1, '게시글1', '게시글내용1', '2021-02-01 01:12:37.000000', 0)
     , (1, 1, '게시글2', '게시글내용2', '2021-02-01 01:12:37.000000', 0);

INSERT INTO BOARD_COMMENT(COMMENTS, REG_DATE, BOARD_ID, USER_ID)
VALUES
('게시글1번에 대한 댓글1', '2021-02-01 01:12:37.000000', 1, 1)
     , ('게시글1번에 대한 댓글2', '2021-02-01 01:12:37.000000', 1, 1)
     , ('게시글2번에 대한 댓글1', '2021-02-01 01:12:37.000000', 2, 1)
     , ('게시글1번에 대한 댓글3', '2021-02-01 01:12:37.000000', 1, 2)
;