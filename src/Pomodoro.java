import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 * Created by Devon on 11/28/2015.
 */
public class Pomodoro {

    //public static class Project {    //Defines Project object, my whole original project was using this and it sucked
    //  String name;
    //long time;
    //public String getName() {
    //  return name;
    //}
    //public void setName(String name) {
    //   this.name = name;
    //}
    //public long getTime() {
    //    return time;
    //}
    //public void setTime(long time) {
    //    this.time = time;
    //}
    //}

    public static void writeData(ArrayList data) {       // Writes to file for add project

        try {
            File file = new File("src/projectlist.txt");

            //if file exists, extract from file and then write
            if (file.exists()) {
                String temp = "";
                FileReader input = new FileReader("src/projectlist.txt");
                BufferedReader br = new BufferedReader(input);
                String str;

                while ((str = br.readLine()) != null) {
                    temp = str;
                }
                input.close();
                br.close();

                FileWriter fw = new FileWriter("src/projectlist.txt");
                temp += data + "  ";

                fw.write(temp);
                fw.close();
            }

            // if file doesn't exists, then create it and then write
            else if (!file.exists()) {
                file.createNewFile();

                FileWriter fw = new FileWriter("src/projectlist.txt");
                String temp = "";
                temp = temp + data + "  ";

                fw.write(temp);
                fw.close();
            }

        } catch (IOException y) {
            System.out.println(y.toString());
            System.out.println("Could not find file ");
        }
    }

    public static ArrayList addProject(String name) {

        ArrayList<String> tempList = new ArrayList();  // Creates Project object and then returns it
        tempList.add(name);
        tempList.add("0");
        return tempList;
    }

    public static void listProjects () {    //Prints out a list of projects
        try {
            FileReader input = new FileReader("src/projectlist.txt");
            BufferedReader br = new BufferedReader(input);
            String str;

            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
            input.close();
            br.close();
        } catch (IOException x) {
            System.out.println(x.toString());
            System.out.println("Could not find file ");
        }
    }

    public static ArrayList chooseProject() {  // Extracts list of projects into an arraylist and then returns it
        try {
            FileReader input = new FileReader("src/projectlist.txt");
            BufferedReader br = new BufferedReader(input);
            String str;

            str = br.readLine();
            List<String> list = new ArrayList<>(Arrays.asList(str.split("  ")));
            ArrayList<String> arraylist = new ArrayList<String>(list);

            input.close();
            br.close();
            return arraylist;
        } catch (IOException x) {
            System.out.println(x.toString());
            System.out.println("Could not find file");
        }
       return null;
    }

    public static void deleteProject(String name, ArrayList data) {
        String temp = "";
        for (int i = 0; i < data.size(); i++) {                //Takes each list and extracts title
            String z = data.get(i).toString();
            String x = data.get(i).toString().replace("[", "");
            int count = x.indexOf(",") - 1;
            String newtemp = "";
            for (int y = 0; y <= count; y++) {
                newtemp += x.charAt(y);
            }
            if (!newtemp.equals(name)) {    //checks extracted title against name and then adds to temp
                temp = temp + z;             // if not deleted list, does the deletion
                temp = temp + "  ";
            }
        }

        try {
            FileWriter fw = new FileWriter("src/projectlist.txt");  // writes list without deleted project to file
            String tempfile = "";
            tempfile = tempfile + temp;

            fw.write(tempfile);
            fw.close();
        } catch (IOException p) {
            System.out.println(p.toString());
            System.out.println("Could not find file");
        }
    }

    public static void editProject (String name, ArrayList data, String edit) {  // Edits a project
        String temp = "";
        for (int i = 0; i < data.size(); i++) {                //Takes each list and extracts title
            String z = data.get(i).toString();
            String x = data.get(i).toString().replace("[", "");
            int count = x.indexOf(",") - 1;
            String newtemp = "";
            for (int y = 0; y <= count; y++) {
                newtemp += x.charAt(y);
            }
            if (!newtemp.equals(name)) {    //checks extracted title against name and then adds it back to a string if
                temp = temp + z;            // if not edited list
                temp = temp + "  ";
            }
            else if (newtemp.equals(name)) { // if title is edited list, the number value is extracted and stored
                String edittime = "";
                for (int ab = count+2; ab <= x.length()-2; ab++) {
                    edittime += x.charAt(ab);
                }
                edit = "[" + edit + "," + edittime + "]  ";   // number value is formatted and added to new name
                temp = temp + edit;                            // then added to another string for storage

            }
        }

        try {
           FileWriter fw = new FileWriter("src/projectlist.txt");    // new string is written to file
            String tempfile = "";
            tempfile = tempfile + temp;

            fw.write(tempfile);
            fw.close();
        } catch (IOException p) {

        }
    }

    public static void interfaceConsole() {
        System.out.print("Welcome to Pomodoro Generator!");  //Initial Prompts
        System.out.println("");
        System.out.println("1. Add Projects, 2. List Projects, 3. Choose Projects" +
                ", 4. Export");

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a command: ");
        int n = reader.nextInt();

        if (n == 1) {                                // runs AddProject Class
            System.out.println("");
            System.out.println("Add Project");
            Scanner in = new Scanner(System.in);
            System.out.println("Enter name of Project: ");
            String projName = in.nextLine();
            writeData(addProject(projName));
            System.out.println("");
            System.out.println(projName + " has been added as a project");
            System.out.println("");
            interfaceConsole();
        }

        if (n == 2) {                               //runs List Projects
            System.out.println("");
            System.out.println("List Projects");
            System.out.println("");
            listProjects();
            System.out.println("");
            interfaceConsole();
        }

        if (n == 3) {                   //runs Choose Project Interface
            System.out.println("");
            System.out.println("Choose Project");
            System.out.println("");
            Scanner in = new Scanner(System.in);
            System.out.println("Enter name of Project: ");
            String projName = in.nextLine();
            // make an input for delete or edit and return to normal interface
            System.out.println("");
            System.out.println("1. Delete Project, 2. Edit Project, 3. Start Timer" +
                    " 4. Interrupt, 5. Export, 6. Return to previous menus");
            System.out.println("");
            Scanner reader2 = new Scanner(System.in);
            System.out.println("Enter a command: ");
            int cn = reader2.nextInt();

            if (cn == 1) {                                    // Delete project function
                deleteProject(projName, chooseProject());
                System.out.println("");
                interfaceConsole();
            }

            else if (cn == 2) {                                // Edits project function
                Scanner reader3 = new Scanner(System.in);
                System.out.println("Enter a new name: ");
                String edit = reader3.nextLine();
                editProject(projName, chooseProject(), edit);
                System.out.println("");
                interfaceConsole();
            }

            else if (cn == 3) {
                System.out.println("");
                System.out.println("Start Timer");
                System.out.println("");
                interfaceConsole();
            }

            else if (cn == 4) {
                System.out.println("");
                System.out.println("Interrupt");
                System.out.println("");
                interfaceConsole();
            }

            else if (cn == 5) {
                System.out.println("");
                System.out.println("Export Project");
                System.out.println("");
                interfaceConsole();
            }

            else if (cn == 6) {
                System.out.println("");
                interfaceConsole();
            }
            else {
                System.out.println("Please enter valid response");
                //interfaceConsole();
            }
            System.out.println("");
            interfaceConsole();
        }
        else if (n == 4) {
            System.out.println("");
            System.out.println("Export Project");
            System.out.println("");
            interfaceConsole();
        }
        else {
            System.out.println("");
            System.out.println("Incorrect input");
            System.out.println("");
            interfaceConsole();
        }
    }

    public static void main(String[] args) {
        interfaceConsole();

    }
}