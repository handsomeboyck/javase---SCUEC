package com.ck.view;
import com.ck.domain.*;
import com.ck.service.*;

public class TeamView {
    private NameListService nls = new NameListService();
    private TeamService ts = new TeamService();

    public TeamView() {
    }

    public void enterMainMenu() {
        boolean loopFlag = true;
        char key = 0;

        do {
            if (key != '1') {
                this.listAllSoldiers();
            }

            System.out.print("1-��ǲ���б�  2-�����ǲ�ӳ�Ա 3-ɾ����ǲ�ӳ�Ա  4-�˳�     ��ѡ��(1-4):");
            key = TSUtility.readMenuSelection();
            switch(key) {
            case '1':
                this.getAllTeam();
                break;
            case '2':
                this.addMember();
                break;
            case '3':
                this.deleteMember();
                break;
            case '4':
                System.out.print("ȷ���Ƿ��˳�(Y/N):");
                char c = TSUtility.readConfirmSelection();
                if (c == 'Y') {
                    loopFlag = false;
                }
            }
        } while(loopFlag);

    }

    public void deleteMember() {
        System.out.println("\n--------------------�����ǲ�ӳ�Ա---------------------\b");
        System.out.print("������Ҫɾ����ʿ��TID��");
        int memberId = TSUtility.readInt();
        System.out.print("ȷ���Ƿ�ɾ��(Y/N)��");
        char key = TSUtility.readConfirmSelection();
        if (key != 'N') {
            try {
                this.ts.removeMember(memberId);
                System.out.println("ɾ���ɹ�");
            } catch (TeamException var4) {
                System.err.println(var4.getMessage());
            }

            TSUtility.readReturn();
        }
    }

    public void addMember() {
        System.out.println("\n--------------------�����ǲ�ӳ�Ա---------------------\b");
        System.out.print("���������ʿ����ID��");
        int id = TSUtility.readInt();

        try {
            Soldier soldier = this.nls.getSoldier(id);
            this.ts.addMember(soldier);
            System.out.println("��ӳɹ�");
        } catch (TeamException var3) {
            System.err.println("���ʧ�ܣ�ԭ��" + var3.getMessage());
        }

        TSUtility.readReturn();
    }

    public void getAllTeam() {
        System.out.println("\n--------------------��ǲ�ӳ�Ա�б�---------------------\n");
        Infantry[] infantry = this.ts.getTeam();
        if (infantry.length == 0) {
            System.out.println("������Ա��Ϣ��");
        } else {
            System.out.println("TID/ID\t����\t����\tս����\t����\tҰ������ָ��\tָ�ӵȼ�");
        }

        for(int i = 0; i < infantry.length; ++i) {
            System.out.println(infantry[i].getDetailsForTeam());
        }

        System.out.println("----------------------------------------------------");
    }

    public void listAllSoldiers() {
        System.out.println("\n------------------------------��ǲս���齨����ϵͳ----------------------------\n");
        Soldier[] soldiers = this.nls.getAllSoldiers();
        if (soldiers.length == 0) {
            System.out.println("û���ҵ�ʿ����");
        } else {
            System.out.println("ID\t����\t����\tս����\t����\t״̬\tҰ������\tָ�ӵȼ�\t�䱸����");
        }

        for(int i = 0; i < soldiers.length; ++i) {
            System.out.println(soldiers[i]);
        }

        System.out.println("-----------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        TeamView tv = new TeamView();
        tv.enterMainMenu();
    }

}
