package com.company;
import java.util.List;
import java.util.*;
import java.util.ArrayList;

public class GreedyHierarchy {

    int currentWeight = 0;
    final int maxLevel = 50;
    int depth = 3;
    private List<Item> itemPath = new ArrayList<Item>();
    ListItem listItem = new ListItem();

    public GreedyHierarchy() {
        System.out.println("Greedy algorithm tree search - find optimal path, locally");
        System.out.println("Depth: " + depth);
        System.out.println("Tree:");
        setItems(); // setting some test items to tree and prints the tree
        System.out.println("----------------------------------------");
        System.out.print("Value of path: ");
        System.out.println(findMostFollowersPath(listItem.firstInItemList(), 0));
        System.out.println("Path: ");
        for (Item item: itemPath){
            System.out.print("Value: ");
            System.out.print(item.getValue());
            System.out.print(" Weight: ");
            System.out.print(item.getWeight());
            System.out.println("");
        }
    }

    public long findMostFollowersPath(Item item, int currentDepth) {
        long max = 0;
        Item toFollow = null;

            if (depth == currentDepth){
                return item.getValue();
            }else{
                List<Item> children = item.getChildren();
                for (Item child : children) {
                    long followersCount = child.getValue();
                    if (followersCount > max) {
                        toFollow = child;
                        max = followersCount;
                    }
                }
            }


        if (currentWeight < maxLevel) {
            itemPath.add(toFollow);
            currentWeight += toFollow.getWeight();
            max += findMostFollowersPath(toFollow, currentDepth+1);
        }
        return max;
    }

    public void setItems(){
        List<Item> rowOne = new ArrayList<Item>();
        List<Item> rowTwo = new ArrayList<Item>();
        List<Item> rowThree = new ArrayList<Item>();

        Item startItem = new Item(2, 5, 0);
        listItem.addItemList(startItem);
        long idnr = 1;
        for (int i = 0; i < 4; i++) {
            Random rn = new Random();
            Item secondItem = new Item(1+rn.nextInt(10), 1+rn.nextInt(10), idnr);
            listItem.addItemList(secondItem);
            rowOne.add(secondItem);
            startItem.addChild(secondItem);
            idnr++;
            for (int j = 0; j < 2; j++) {
                Item item = new Item(1+rn.nextInt(10), 1+rn.nextInt(20), idnr);
                listItem.addItemList(item);
                rowTwo.add(item);
                secondItem.addChild(item);
                idnr++;
                for (int k = 0; k < 2; k++) {
                    Item lastItem = new Item(1+rn.nextInt(10), 1+rn.nextInt(20), idnr);
                    listItem.addItemList(lastItem);
                    rowThree.add(lastItem);
                    item.addChild(lastItem);
                    idnr++;
                }
            }
        }
        printTree(rowOne, rowTwo, rowThree);
    }

    public void printTree(List<Item> one, List<Item> two, List<Item> three) {
        System.out.print("V");
        System.out.print(listItem.firstInItemList().getValue());
        System.out.print("W");
        System.out.print(listItem.firstInItemList().getWeight());
        System.out.println("");
        for (Item child : one) {
            System.out.print("V");
            System.out.print(child.getValue());
            System.out.print("W");
            System.out.print(child.getWeight());
            System.out.print("---");
        }
        System.out.println(" ");
        for (Item child : two) {
            System.out.print("V");
            System.out.print(child.getValue());
            System.out.print("W");
            System.out.print(child.getWeight());
            System.out.print("---");
        }
        System.out.println(" ");
        for (Item child : three) {
            System.out.print("V");
            System.out.print(child.getValue());
            System.out.print("W");
            System.out.print(child.getWeight());
            System.out.print("---");
        }
        System.out.println("");
    }

}