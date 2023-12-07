package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");

        List<WiseSaying> wiseSayingList = new ArrayList();
        int lastId = 1;
        while (true) {

            System.out.print("명령) ");

            String command = sc.nextLine();
            Request request = new Request();
            request.commandSplit(command);
            String actionCode = request.getActionCode();

            if (actionCode.equals("등록")) {

                System.out.print("명언 : ");
                String content = sc.nextLine().trim();
                System.out.print("작가 : ");
                String author = sc.nextLine().trim();
                WiseSaying ws = new WiseSaying(lastId, content, author);
                wiseSayingList.add(ws);
                lastId++;
            } else if (actionCode.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (WiseSaying ws : wiseSayingList) {
                    System.out.println(ws.getId() + " / " + ws.getAuthor() + " / " + ws.getContent());
                }
            } else if (actionCode.equals("종료")) {
                break;
            } else if (actionCode.equals("삭제")) {
                for (int i = 0; i < wiseSayingList.size(); i++) {
                    if (wiseSayingList.get(i).getId() == request.getIdNumber()) {
                        wiseSayingList.remove(wiseSayingList.get(i));
                    }
                }
            } else if (actionCode.equals("수정")) {
                for (int i = 0; i < wiseSayingList.size(); i++) {
                    if (wiseSayingList.get(i).getId() == request.getIdNumber()) {
                        System.out.println("기존 작가 : " + wiseSayingList.get(i).getAuthor());
                        System.out.print("작가 수정 : ");
                        String newAuthor = sc.nextLine();

                        wiseSayingList.get(i).setAuthor(newAuthor);

                        System.out.println("기존 명언 : " + wiseSayingList.get(i).getContent());
                        System.out.print("명언 수정 : ");
                        String newContent = sc.nextLine();

                        wiseSayingList.get(i).setContent(newContent);

                    }
                    System.out.println(request.getIdNumber() + "번 명언이 수정되었습니다.");
                }
            }
        }
    }
}

class WiseSaying {
    private int id;
    private String content;
    private String author;

    WiseSaying(int lastId, String content, String author) {
        this.id = lastId;
        this.content = content;
        this.author = author;
    }

    int getId() {
        return this.id;
    }

    String getContent() {
        return this.content;
    }

    String getAuthor() {
        return this.author;
    }

    void setContent(String content) {
        this.content = content;
    }

    void setAuthor(String author) {
        this.author = author;
    }
}

class Request {
    private String actionCode;
    private int idNumber;

    public void commandSplit(String command) {
        String[] questionMark = command.split("\\?");
        this.actionCode = questionMark[0];

        if (questionMark.length == 1) return;

        String[] ampersand = questionMark[1].split("&");
        String[] equal = ampersand[ampersand.length - 1].split("=");
        this.idNumber = Integer.parseInt(equal[1]);
    }

    String getActionCode() {
        return this.actionCode;
    }

    int getIdNumber() {
        return this.idNumber;
    }
}