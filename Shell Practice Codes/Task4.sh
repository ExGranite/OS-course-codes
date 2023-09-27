#!/bin/bash

echo "Enter a number:"
read n

if [ $(( $n % 2 )) -eq 0 -a $(( $n % 3 )) -eq 0 ];
	then echo "Hello"		
elif [ $(( $n % 2 )) -eq 0 -o $(( $n % 3 )) -eq 0 ];
	then echo "Nihao"
else
	echo "Hola"
fi