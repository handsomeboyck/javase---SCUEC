package com.ck.service;
import com.ck.domain.*;

public class TeamService {
    private static int counter = 1;
    private final int MAX_MEMBER = 5;
    private Infantry[] team = new Infantry[5];
    private int total = 0;

    public TeamService() {
    }

    public Infantry[] getTeam() {
        Infantry[] ins = new Infantry[this.total];

        for(int i = 0; i < ins.length; ++i) {
            ins[i] = this.team[i];
        }

        return ins;
    }

    public void addMember(Soldier soldier) throws TeamException {
        if (this.total >= 5) {
            throw new TeamException("��ǲ�ӳ�Ա�������޷����");
        } else if (!(soldier instanceof Infantry)) {
            throw new TeamException("��ʿ������ͨʿ�����޷����");
        } else {
            Infantry infantry = (Infantry)soldier;
            Status status = infantry.getStatus();
            switch(status.ordinal()) {
            case 2:
                throw new TeamException("��ʿ��������ǲ��Ա");
            case 3:
                throw new TeamException("��ʿ�������ݼ٣��޷����");
            default:
                int numOfcom;
                for(numOfcom = 0; numOfcom < this.total; ++numOfcom) {
                    if (infantry.getId() == this.team[numOfcom].getId()) {
                        throw new TeamException("��ʿ��������ǲ��Ա");
                    }
                }

                numOfcom = 0;
                int numOfspt = 0;
                int numOfinf = 0;

                for(int i = 0; i < this.total; ++i) {
                    if (this.team[i] instanceof Commander) {
                        ++numOfcom;
                    } else if (this.team[i] instanceof SpecialTroops) {
                        ++numOfspt;
                    } else if (this.team[i] instanceof Infantry) {
                        ++numOfinf;
                    }
                }

                if (infantry instanceof Commander) {
                    if (numOfcom >= 1) {
                        throw new TeamException("��ǲ����ֻ����һ��ָ�ӹ�");
                    }
                } else if (infantry instanceof SpecialTroops) {
                    if (numOfspt >= 2) {
                        throw new TeamException("��ǲ����ֻ�����������ֱ�");
                    }
                } else if (infantry instanceof Infantry && numOfinf >= 3) {
                    throw new TeamException("��ǲ����ֻ������������");
                }

                infantry.setMemeberId(counter++);
                infantry.setStatus(Status.BUSY);
                this.team[this.total++] = infantry;
            }
        }
    }

   

	public void removeMember(int memberId) throws TeamException {
        int i;
        label27:
        for(i = 0; i < this.total; ++i) {
            if (this.team[i].getMemberId() == memberId) {
                this.team[i].setStatus(Status.FREE);
                int j = i + 1;

                while(true) {
                    if (j >= this.total) {
                        break label27;
                    }

                    this.team[j - 1] = this.team[j];
                    ++j;
                }
            }
        }

        if (i == this.total) {
            throw new TeamException("��ǲ����û���ҵ��ó�Ա��");
        } else {
            this.team[--this.total] = null;
        }
    }

    public static void main(String[] args) {
        TeamService ts = new TeamService();
        NameListService nls = new NameListService();
        Soldier[] soldiers = nls.getAllSoldiers();
        System.out.println("-----------����ʿ��----------");
        Soldier[] var7 = soldiers;
        int var6 = soldiers.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            Soldier so = var7[var5];
            System.out.println(so);
        }

        System.out.println("-----------��ǲ��ʿ��----------");

        try {
            ts.addMember(soldiers[11]);
            ts.addMember(soldiers[1]);
            ts.addMember(soldiers[9]);
        } catch (TeamException var10) {
            System.err.println(var10.getMessage());
        }

        try {
            ts.removeMember(3);
        } catch (TeamException var9) {
            System.out.println(var9.getMessage());
        }

        Infantry[] inf = ts.getTeam();
        Infantry[] var8 = inf;
        int var13 = inf.length;

        for(var6 = 0; var6 < var13; ++var6) {
            Infantry in = var8[var6];
            System.out.println(in.getDetailsForTeam());
        }

    }
	

}
