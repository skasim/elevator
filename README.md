# Lab 1 - Elevator Simulation by Samra Kasim

## To Run the program
* In terminal, `cd` to the `src` directory in the project directory structure.
* To compile the program run `javac $(find . -name '*.java')` from the `src` directory.
* To run the program, make sure to be in the `src` directory and run:
`java com.sk.elevator.Elevator ../input/elevator_input_data.txt ../output/elevator_input_data_out.txt` to process provided data and
`java com.sk.elevator.Elevator ../input/student_input_data.txt ../output/student_input_data_out.txt` to process student generated data.

## Errata
* If providing comments in the input file, make sure they are preceded by `//`.
* The write to output file appends to the last entry, so if generating a new file, delete the older version or update the output filename.
* The two input files are provided in the `input` directory. They are `elevator_input_data.txt` and `student_input_data.txt`.
* The output files are in the `output` directory. They are `elevator_input_data_out.txt` and `student_input_data_out.txt`.
* Java version 1.8 and IDE is IntelliJ