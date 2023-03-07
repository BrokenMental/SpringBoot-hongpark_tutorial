INSERT INTO article(id, title, content) VALUES (DEFAULT, '가가가가', '1111');
INSERT INTO article(id, title, content) VALUES (DEFAULT, '나나나나', '2222');
INSERT INTO article(id, title, content) VALUES (DEFAULT, '다다다다', '3333');

-- article 더미 데이터
INSERT INTO article(id, title, content) VALUES (DEFAULT, '당신의 인생 영화는?', 'ㄱ');
INSERT INTO article(id, title, content) VALUES (DEFAULT, '당신의 소울 푸드는?', 'ㄱㄱ');
INSERT INTO article(id, title, content) VALUES (DEFAULT, '당신의 취미는?', 'ㄱㄱㄱ');

-- comment 더미 데이터
-- 4번 댓글
INSERT INTO comment(id, article_id, nickname, body) VALUES (DEFAULT, 4, 'Park', '굿 윌 헌팅');
INSERT INTO comment(id, article_id, nickname, body) VALUES (DEFAULT, 4, 'Kim', '아이 엠 샘');
INSERT INTO comment(id, article_id, nickname, body) VALUES (DEFAULT, 4, 'Choi', '쇼생크 탈출');

-- 5번 댓글
INSERT INTO comment(id, article_id, nickname, body) VALUES (DEFAULT, 5, 'Park', '치킨');
INSERT INTO comment(id, article_id, nickname, body) VALUES (DEFAULT, 5, 'Kim', '샤브샤브');
INSERT INTO comment(id, article_id, nickname, body) VALUES (DEFAULT, 5, 'Choi', '초밥');

-- 6번 댓글
INSERT INTO comment(id, article_id, nickname, body) VALUES (DEFAULT, 6, 'Park', '조깅');
INSERT INTO comment(id, article_id, nickname, body) VALUES (DEFAULT, 6, 'Kim', 'youtube');
INSERT INTO comment(id, article_id, nickname, body) VALUES (DEFAULT, 6, 'Choi', '독서');