<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    new daum.Postcode({
        oncomplete: function(data) {
            if(data.userSelectedType=="R"){
                // userSelectedType : 검색 결과에서 사용자가 선택한 주소의 타입

                // return type : R - roadAddress, J : jibunAddress

                // PoscApp 은 안드로이드에서 등록한 이름
                window.PoscApp.setAddress(data.zonecode, data.roadAddress, data.buildingName);
            }
            else{
                window.PoscApp.setAddress(data.zonecode, data.jibunAddress, data.buildingName);
            }       
        }
    }).open();
</script>
</body>
</html>