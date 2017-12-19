#!/bin/bash
read  -p  "Please  input  yes  or  no:  "  ANS
case  "$ANS"  in
　　[Yy]  |  yes  |  YES  )
　　echo  “You  mean  YES!!”;;
　　[Nn]  |  no  |  NO  )
　　echo  “You  mean  NO!!”;;
　　*  )
　　echo  “byebye”;;
esac