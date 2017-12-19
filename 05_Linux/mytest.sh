#!  /bin/bash
if  [  “$1” ==  “hello”  ];  then
    echo  "world"
elif  [  “$1”  ==  “”  ];  then     #沒有傳進參數
    echo  "Please  input  parameters"
else
    echo  "wrong  parameter"
fi
