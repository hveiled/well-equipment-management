# well-equipment-management

Allows creating, printing and exporting to XML file information about Oil wells and its equipment.
<hr>

## How to build:

```text
mvn package
``` 

## How to start:

```text
mvn -jar target/well-equipment-manager-1.0-SNAPSHOT-launcher.jar
```

<hr>

## Avalilable commands:

```text
Enter 'h' for Help
Enter '1' to create Well
Enter '2' to print table containing information about Wells
Enter '3' to export to XML
Enter '4' to Exit
```

# Examples of programm behaviour in case of:

## __Printing data__

will be printed to the console

```text
EQUIPPED OIL WELLS
______________________________________________________________
|                       Well name |  Equipment amount, items |
______________________________________________________________
|                           Alpha |                        5 |
|                           Bravo |                       10 |
______________________________________________________________
|                           Total |                       15 |
______________________________________________________________
```

## __Export data__

will be created a file with the specified name

```xml

<dbinfo>
    <well id="1" name="Alpha">
        <equipment id="1" name="EQ0001"/>
        <equipment id="2" name="EQ0002"/>
        <equipment id="3" name="EQ0003"/>
        <equipment id="4" name="EQ0004"/>
        <equipment id="5" name="EQ0005"/>
    </well>
    <well id="2" name="Bravo">
        <equipment id="6" name="EQ0006"/>
        <equipment id="7" name="EQ0007"/>
        <equipment id="8" name="EQ0008"/>
        <equipment id="9" name="EQ0009"/>
        <equipment id="10" name="EQ0010"/>
        <equipment id="11" name="EQ0011"/>
        <equipment id="12" name="EQ0012"/>
        <equipment id="13" name="EQ0013"/>
        <equipment id="14" name="EQ0014"/>
        <equipment id="15" name="EQ0015"/>
    </well>
</dbinfo>
```
