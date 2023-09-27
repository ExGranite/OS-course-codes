#!/bin/bash

echo "Which operation would you like to do?"
set -f
read op

echo "Enter operand 1:"
read op1
echo "Enter operand 2:"
read op2

add(){
	result=$(( $1 + $2))
	return $result
}
sub(){
	result=$(( $1 - $2))
	return $result
}
mul(){
	result=$(( $1 * $2))
	return $result
}
div(){
	result=$(( $1 / $2))
	return $result
}

if [ $op = "+" ];
	then
		add $op1 $op2
		echo "The result is $?."
elif [ $op = "-" ];
	then
		sub $op1 $op2
		echo "The result is $?."
elif [ $op = "*" ];
	then
		mul $op1 $op2
		echo "The result is $?."
elif [ $op = "/" ];
	then
		div $op1 $op2
		echo "The result is $?."
else
	echo "Invalid Operation"
fi