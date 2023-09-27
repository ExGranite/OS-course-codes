#!/bin/bash

echo "Enter a number:"
read n

square(){
	num=$1
    rem=0
    sum=0
    while [ $num -gt 0 ];
		do
            rem=$(($num%10))
			pow=$(($rem*$rem))
            sum=$(($sum+$pow))
            num=$(($num/10))
		done
    return $sum
}

happy(){
	result=$1

	while [ $result -ne 1 -a $result -ne 4 ]
	    do
            square $result
		    result=$?
	    done
	if [ $result -eq 1 ];
		then return 1
	else
        return 0
	fi
}

prime(){
	c=0
	if [ $1 -ne 0 ];
	then
		for ((i=1; i<=$1; i++))
			do
				if [ $(( $1 % $i )) -eq 0 ];
					then c=$((c+1))
				fi
			done
		if [ $c -lt 3 ];
			then return 1
		else
			return 0
		fi
	else
		return 0
	fi
}

prime $n
if [ $? -eq 1 ];
	then
		happy $n
		if [ $? -eq 1 ];
			then echo "It is a Happy Prime"
		else
			echo "It is not a Happy Prime"
		fi
else
	echo "It is not a Happy Prime"
fi