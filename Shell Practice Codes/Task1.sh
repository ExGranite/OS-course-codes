#!/bin/bash

echo "Enter your BRACU ID:"
read id

if [ ${#id} -eq 8 ];
    then
		year="20${id:0:2}"
		if [ ${id:3:2} -eq 01 ];
			then
				if [ ${id:2:1} -eq 1 ];
					then 
						echo "The student is from the Dept. of CSE enrolled in Spring $year."
				elif [ ${id:2:1} -eq 2 ];
					then
						echo "The student is from the Dept. of CSE enrolled in Fall $year."
				elif [ ${id:2:1} -eq 3 ] ;
					then
						echo "The student is from the Dept. of CSE enrolled in Summer $year."
				fi
		else
			echo "The student is not from the Dept. of CSE."
		fi
		
else
	echo 'Invalid BRACU ID.'
fi