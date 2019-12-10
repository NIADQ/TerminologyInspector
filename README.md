# 공공데이터 용어형식 표준화 정비도구 Repository 구성 

## TerminologyInspector 폴더
   * src/application 폴더
       1. Main.java            : 메인 클래스
       2. MainController.java  : 메인에서 사용할 클래스
       3. util 폴더
          - ChangeBizNo.java   : 사업자번호 정비
          - ChangeDate.java    : 날짜 정비
          - ChangeNumeric.java : 숫자 정비
          - ChangeTelNo.java   : 전화번호 정비
          - ChangeYn.java      : 여부 정비
          - ChangeZipNo.java   : 우편번호 정비

## 배포형식(라이센스)

**GNU GPL**으로 자체 용어·형식 일괄 정비도구의 자바 FX 버전은 누구나 소스를 이용할 수 있음. 기본적으로 어떤 프로그램을 개발할 때, GPL 코드를 일부라도 사용하게 되면 그 프로그램은 GPL이 됨. GPL을 가진 프로그램을 유료로 판매하는 것은 가능하지만, 반드시 전체 소스코드는 무료로 공개해야 함. GPL 코드를 사용한 SW를 내부적인(개인, 기관, 단체 등) 목적으로만 사용할 때에는 소스코드를 공개할 필요가 없지만 어떤 형태로든(유료든 무료든) 외부에 공표/배포할 때에는 전체 소스코드를 공개해야 함. 
