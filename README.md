# COVID-19-MIL-CRAWLER
군복무동안 제작한 코로나 크롤러 프로젝트

개발자 차승일과 개발자 남하욱은 국군수송사령부 3철도대 소속 대전 TMO병이었다. TMO병이란 휴가/출장가는 국군장병들을 위해 기차 여비 지급을 하는 주특기를 가진 병이다.

3철도대에는 20여개의 수많은 TMO가 존재하는데 전부 거리가 매우 떨어져있다. 

2020 / 2021년에는 코로나 상황에 따라 제한적 휴가/외출을 시행하였는데 이에 따라 사령부에서는 매일 TMO가 위치하는 지역의 구 기준으로 일일 코로나 확진자 수를 수집하고 있었다. 

대형 포털사이트는 대형도시(서울, 부산 등등)으로만 일일 확진자가 나와있고, 동/군 단위로 나와있지 않다.

따라서 매일 군 간부들이 각 시/군 별로 수십개의 사이트를 들어가 업데이트 여부를 확인하며 매우 자주 들어가 체크한다.

이 과정이 매우 번거로워 보였던 개발자 차승일과 개발자 남하욱은 평소에 공부하던 크롤링/자바/HTML 지식을 이용하여 매일 정해진 시간에 TMO가 존재하는 지역들의 코로나 환자수를 크롤링하여 군 간부들 네이버 메일에 자동으로 보내주는 시스템을 제작하였다.

또한 국군수송 사령부 예야 부대인 1철도대, 2철도대, 호송대대, 항만대으로 확장하여(약 90개의 시/군)그 부대의 간부들에게도 메일이 가도록 제작을 하였다.

크롤러는 매일 어제, 오늘 각 지역 환자수를 1Fe(1철도대), 2Fe(2철도대), 3Fe(3철도대), hosong(호송대대), hangman(항만단)파일에 갱신하고, 이에따라 코로나 미발생 일수를 갱신한다. Jsoup과 Javamail 라이브러리를 활용하여 개발하였다.
