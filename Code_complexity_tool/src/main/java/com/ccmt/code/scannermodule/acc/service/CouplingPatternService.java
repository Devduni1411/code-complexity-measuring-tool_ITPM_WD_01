package com.ccmt.code.scannermodule.acc.service;

import com.ccmt.code.coremodule.model.CouplingComplexity;
import com.ccmt.code.scannermodule.acc.enums.Method;

import java.util.ArrayList; // import the ArrayList class
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CouplingPatternService {

    public List<Method> getMethodsList(File file) throws FileNotFoundException {
        final Scanner scan = new Scanner(file);
        int index = 0;
        final ArrayList<String> words = new ArrayList<String>();
        final ArrayList<Method> list = new ArrayList<Method>();
        final ArrayList<String> globalVariables = new ArrayList<String>();
        int count = 0;


        scan.useDelimiter("[\\s;=+-,/*}{]+");
        while (scan.hasNext()) {
            words.add(scan.next());
            index++;
        }
        int o = 0;
        //obtaining global variables
        while (findMethod(words, o) == null) {
            if (words.get(o).equals("int")) {
                String[] temp = words.get(o + 1).split("[)(]+");
                globalVariables.add(temp[0]);
            }
            o++;
            if (o == index - 10) {
                break;
            }
        }

        //obtaining regular and recursive methods
        for (int i = 0; i < index; i++) {
            String methodName = findMethod(words, i);
            if (i == index - 4) {

            } else if (methodName != null) {
                String methodType = "regular";
                int start = i + 4;
                int temparary = start;
                while (findMethod(words, start) == null) {
                    if (words.get(start).contains(methodName)) {
                        String[] temp = words.get(start).split("[)(]+");
                        for (String a : temp) {
                            if (a.equals(methodName)) {
                                methodType = "recursive";
                                break;
                            }
                        }
                    }
                    //start
                    System.out.println(start);
                    start++;
                    if (start == index - 10) {
                        break;
                    }
                }
                int end = start - 1;
                Method obj = new Method(methodType, methodName, temparary, end);
                list.add(obj);
            }
        }
        return list;
    }

    public int getData(String fileName) throws FileNotFoundException, NoSuchElementException {
        final File file = new File(fileName);
        final Scanner scan = new Scanner(file);
        int index = 0;
        final ArrayList<String> words = new ArrayList<String>();
        final ArrayList<Method> list = new ArrayList<Method>();
        final ArrayList<String> globalVariables = new ArrayList<String>();
        int count = 0;


        scan.useDelimiter("[\\s;=+-,/*}{]+");
        while (scan.hasNext()) {
            words.add(scan.next());
            index++;
        }
        int o = 0;
        //obtaining global variables
        while (findMethod(words, o) == null) {
            if (words.get(o).equals("int")) {
                String[] temp = words.get(o + 1).split("[)(]+");
                globalVariables.add(temp[0]);
            }
            o++;
            if (o == index - 10) {
                break;
            }
        }

        //obtaining regular and recursive methods
        for (int i = 0; i < index; i++) {
            String methodName = findMethod(words, i);
            if (i == index - 4) {

            } else if (methodName != null) {
                String methodType = "regular";
                int start = i + 4;
                int temparary = start;
                while (findMethod(words, start) == null) {
                    if (words.get(start).contains(methodName)) {
                        String[] temp = words.get(start).split("[)(]+");
                        for (String a : temp) {
                            if (a.equals(methodName)) {
                                methodType = "recursive";
                                break;
                            }
                        }
                    }
                    //start
                    System.out.println(start);
                    start++;
                    if (start == index - 10) {
                        break;
                    }
                }
                int end = start - 1;
                Method obj = new Method(methodType, methodName, temparary, end);
                list.add(obj);
            }
        }
        int length = list.size();

        for (int i = 0; i < length; i++) {
            int s = list.get(i).getStartIndex();
            int e = list.get(i).getEndIndex();
            String n = list.get(i).getName();
            String t = list.get(i).getType();

            for (int j = s; j < e; j++) {
                for (int z = 0; z < length; z++) {
                    if (z == i) continue;
                    if (words.get(j).contains(list.get(z).getName())) {
                        String[] temp = words.get(j).split("[)(]+");
                        for (String a : temp) {
                            if (a.equals(list.get(z).getName())) {
                                if (t.equals("regular")) {
                                    if (list.get(z).getType().equals("regular")) {
                                        //"regular calls regular"
                                        System.out.println("regular calls regular");
                                        count = count + 2;
                                    } else {
                                        //"regular calls recursive"
                                        System.out.println("regular calls recursive");
                                        count = count + 3;
                                    }
                                } else if (t.equals("recursive")) {
                                    if (list.get(z).getType().equals("regular")) {
                                        //"recursive calls regular"
                                        System.out.println("recursive calls regular");
                                        count = count + 3;
                                    } else {
                                        //"recursive calls recursive"
                                        System.out.println("recursive calls recursive");
                                        count = count + 4;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        int gLength = globalVariables.size();
        for (int i = 0; i < length; i++) {
            int s = list.get(i).getStartIndex();
            int e = list.get(i).getEndIndex();
            // String n = list.get(i).name;
            String t = list.get(i).getType();
            //s + "start"
            System.out.println(s + "start");
            //e + "end"
            System.out.println(e + "end");
            for (int j = s; j < e; j++) {
                for (int z = 0; z < gLength; z++) {
                    if (words.get(j).contains(globalVariables.get(z))) {
                        //globalVariables.get(z)
                        System.out.println(globalVariables.get(z));
                        String[] temp = words.get(j).split("[)(]+");
                        for (String a : temp) {
                            if (a.equals(globalVariables.get(z))) {
                                if (t.equals("recursive")) {
                                    //"recursive calls global"
                                    System.out.println("recursive calls global");
                                    count = count + 1;
                                } else {
                                    //"regular calls global"
                                    System.out.println("regular calls global");
                                    count = count + 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;

    }

    public CouplingComplexity getCouplingComplexity(File file) throws FileNotFoundException, NoSuchElementException {
        final Scanner scan = new Scanner(file);
        CouplingComplexity couplingComplexity = new CouplingComplexity();
        int index = 0;
        final ArrayList<String> words = new ArrayList<String>();
        final ArrayList<Method> list = new ArrayList<Method>();
        final ArrayList<String> globalVariables = new ArrayList<String>();
        int count = 0;


        scan.useDelimiter("[\\s;=+-,/*}{]+");
        while (scan.hasNext()) {
            words.add(scan.next());
            index++;
        }
        int o = 0;
        //obtaining global variables
        while (findMethod(words, o) == null) {
            if (words.get(o).equals("int")) {
                String[] temp = words.get(o + 1).split("[)(]+");
                globalVariables.add(temp[0]);
            }
            o++;
            if (o == index - 10) {
                break;
            }
        }

        //obtaining regular and recursive methods
        for (int i = 0; i < index; i++) {
            String methodName = findMethod(words, i);
            if (i == index - 4) {

            } else if (methodName != null) {
                String methodType = "regular";
                int start = i + 4;
                int temparary = start;
                while (findMethod(words, start) == null) {
                    if (words.get(start).contains(methodName)) {
                        String[] temp = words.get(start).split("[)(]+");
                        for (String a : temp) {
                            if (a.equals(methodName)) {
                                methodType = "recursive";
                                break;
                            }
                        }
                    }
                    //start
                    System.out.println(start);
                    start++;
                    if (start == index - 10) {
                        break;
                    }
                }
                int end = start - 1;
                Method obj = new Method(methodType, methodName, temparary, end);
                list.add(obj);
            }
        }
        int length = list.size();

        for (int i = 0; i < length; i++) {
            int s = list.get(i).getStartIndex();
            int e = list.get(i).getEndIndex();
            String n = list.get(i).getName();
            String t = list.get(i).getType();

            for (int j = s; j < e; j++) {
                for (int z = 0; z < length; z++) {
                    if (z == i) continue;
                    if (words.get(j).contains(list.get(z).getName())) {
                        String[] temp = words.get(j).split("[)(]+");
                        for (String a : temp) {
                            if (a.equals(list.get(z).getName())) {
                                if (t.equals("regular")) {
                                    if (list.get(z).getType().equals("regular")) {
                                        //"regular calls regular"
                                        System.out.println("regular calls regular");
                                        couplingComplexity.setRegularMethodCallingAnotherRegularMethodInaDifferentFile(
                                                couplingComplexity.getRegularMethodCallingAnotherRegularMethodInaDifferentFile() + 1
                                        );
                                        count = count + 2;
                                    } else {
                                        //"regular calls recursive"
                                        System.out.println("regular calls recursive");
                                        couplingComplexity.setRegularMethodCallingARecursiveMethodInaDifferentFile(
                                                couplingComplexity.getRegularMethodCallingARecursiveMethodInaDifferentFile() + 1
                                        );
                                        count = count + 3;
                                    }
                                } else if (t.equals("recursive")) {
                                    if (list.get(z).getType().equals("regular")) {
                                        //"recursive calls regular"
                                        System.out.println("recursive calls regular");
                                        couplingComplexity.setRecursiveMethodCallingARegularMethodInaDifferentFile(
                                                couplingComplexity.getRecursiveMethodCallingARegularMethodInaDifferentFile() + 1
                                        );
                                        count = count + 3;
                                    } else {
                                        //"recursive calls recursive"
                                        System.out.println("recursive calls recursive");
                                        couplingComplexity.setRecursiveMethodCallingAnotherRecursiveMethodInaDifferentFile(
                                                couplingComplexity.getRecursiveMethodCallingAnotherRecursiveMethodInaDifferentFile() + 1
                                        );
                                        count = count + 4;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        int gLength = globalVariables.size();
        for (int i = 0; i < length; i++) {
            int s = list.get(i).getStartIndex();
            int e = list.get(i).getEndIndex();
            // String n = list.get(i).name;
            String t = list.get(i).getType();
            //s + "start"
            System.out.println(s + "start");
            //e + "end"
            System.out.println(e + "end");
            for (int j = s; j < e; j++) {
                for (int z = 0; z < gLength; z++) {
                    if (words.get(j).contains(globalVariables.get(z))) {
                        //globalVariables.get(z)
                        System.out.println(globalVariables.get(z));
                        String[] temp = words.get(j).split("[)(]+");
                        for (String a : temp) {
                            if (a.equals(globalVariables.get(z))) {
                                if (t.equals("recursive")) {
                                    //"recursive calls global"
                                    System.out.println("recursive calls global");
                                    couplingComplexity.setRecursiveMethodReferencingAGlobalVariableInTheSameFile(
                                            couplingComplexity.getRecursiveMethodReferencingAGlobalVariableInTheSameFile() + 1
                                    );
                                    count = count + 1;
                                } else {
                                    //"regular calls global"
                                    System.out.println("regular calls global");
                                    couplingComplexity.setRegularMethodReferencingAGlobalVariableInTheSameFile(
                                            couplingComplexity.getRegularMethodReferencingAGlobalVariableInTheSameFile() + 1
                                    );
                                    count = count + 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return couplingComplexity;

    }

    public boolean isMethod(String codeLine) {
        Pattern pattern = Pattern.compile("(\\s+|\\}|\\{|;|^|\\)|\\(|=|.)([a-zA-Z0-9_]+)\\((.*?)\\)");
        Matcher patternMatcher = pattern.matcher(codeLine);
        return patternMatcher.find();
    }

    public String getMethodName(String cleanedCodeLine) {
        Pattern pattern = Pattern.compile("(\\s+|\\}|\\{|;|^|\\)|\\(|=|.)([a-zA-Z0-9_]+)\\((.*?)\\)");
        Matcher patternMatcher = pattern.matcher(cleanedCodeLine);

        if (patternMatcher.find()) {
            Pattern nameWithBracketOpenPattern = Pattern.compile("([a-zA-Z0-9_]+)\\(");
            Matcher nameWithBracketOpenPatternMatcher = nameWithBracketOpenPattern.matcher(patternMatcher.group());

            if (nameWithBracketOpenPatternMatcher.find()) {
                Pattern namePattern = Pattern.compile("([a-zA-Z0-9_]+)");
                Matcher namePatternMatcher = namePattern.matcher(nameWithBracketOpenPatternMatcher.group());

                return namePatternMatcher.find() ? namePatternMatcher.group() : null;
            }
        }
        return null;
    }

    static String findMethod(ArrayList<String> words, int i) {
        try {
            String[] arr = words.get(i).split("\\[");
            String temp = arr[0];
            if ((temp.equals("void") || temp.equals("int") || temp.equals("float") || temp.equals("double") ||
                    temp.equals("byte") || temp.equals("short") || temp.equals("long") || temp.equals("boolean") ||
                    temp.equals("char") || temp.equals("String") || temp.startsWith("HashMap") ||
                    temp.startsWith("List")) && words.get(i + 2).startsWith("(")) {
                return words.get(i + 1);
            } else if ((temp.equals("void") || temp.equals("int") || temp.equals("float") || temp.equals("double") ||
                    temp.equals("byte") || temp.equals("short") || temp.equals("long") || temp.equals("boolean") ||
                    temp.equals("char") || temp.equals("String") || temp.startsWith("Array") || temp.startsWith("List"))
                    && words.get(i + 1).contains("(")) {
                String[] arrOfStr = words.get(i + 1).split("[(]+");
                return arrOfStr[0];
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}


