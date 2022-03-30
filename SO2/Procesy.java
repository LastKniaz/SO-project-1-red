package SO2;

import java.util.ArrayList;
import java.util.Collections;

public class Procesy {
    private ArrayList<Proces> procesy = new ArrayList<>();

    public Procesy(int proc) {
        for (int i = 0; i < proc; i++)
            this.procesy.add(new Proces(proc));
    }
    public ArrayList<Proces> getProcesy() {
        return procesy;
    }

    public double FCFS() {
        ArrayList<Proces> procesyCopy = new ArrayList<>();
        for (Proces proces: procesy) {
            procesyCopy.add(new Proces(proces));
        }
        ArrayList<Proces> availableProcesy = new ArrayList<>();
        int t = 0, czasOczkoncowy = 0;

        while (procesyCopy.size() != 0 || availableProcesy.size() != 0) {
            updateAvailible(procesyCopy, availableProcesy, t);
            if (availableProcesy.size() != 0) {
                for (int i = 0; i < availableProcesy.get(0).getDlugoscProcesu(); i++) {
                    updateAvailible(procesyCopy, availableProcesy, t);
                    availableProcesy.get(0).decreaseCzasDoKoncaWykon(1);
                    czasOczkoncowy += availableProcesy.size() - 1;
                    t++;
                }
                availableProcesy.remove(0);
            } else t++;
        }

        return czasOczkoncowy;
    }

    public double SJF() {
        ArrayList<Proces> procesyCopy = new ArrayList<>();
        for (Proces proces: procesy) {
            procesyCopy.add(new Proces(proces));
        }
        ArrayList<Proces> availableProcesy = new ArrayList<>();
        int t = 0, czasOczKoncowy = 0;
        Proces currentProces = null;

        updateAvailible(procesyCopy, availableProcesy, t);

        while (procesyCopy.size() != 0 || availableProcesy.size() != 0 || currentProces != null) {
            if (availableProcesy.size() != 0 || currentProces != null) {
                Collections.sort(availableProcesy, new ComparatorSJF());
                if (currentProces == null) {
                    currentProces = availableProcesy.get(0);
                    availableProcesy.remove(0);
                }
                currentProces.decreaseCzasDoKoncaWykon(1);
                if (currentProces.getCzasDoKoncaWykon() <= 0) {
                    currentProces = null;
                }
            }
            czasOczKoncowy += availableProcesy.size();
            t++;
            updateAvailible(procesyCopy, availableProcesy, t);
        }

        return czasOczKoncowy;
    }


    public double SRJF() {
        ArrayList<Proces> procesyCopy = new ArrayList<>();
        for (Proces proces: procesy) {
            procesyCopy.add(new Proces(proces));
        }
        ArrayList<Proces> availableProcesy = new ArrayList<>();
        Proces currentProces = null;
        int t = 0, czasOczKoncowy = 0;

        updateAvailible(procesyCopy, availableProcesy, t);

        while (procesyCopy.size() != 0 || availableProcesy.size() != 0 || currentProces != null) {
            if (availableProcesy.size() != 0) {
                Collections.sort(availableProcesy, new ComparatorSJF());
                if (currentProces != null) {
                    if (currentProces.getCzasDoKoncaWykon() > availableProcesy.get(0).getCzasDoKoncaWykon()) {
                        availableProcesy.add(currentProces);
                        currentProces = availableProcesy.get(0);
                        availableProcesy.remove(0);
                    }
                } else {
                    currentProces = availableProcesy.get(0);
                    availableProcesy.remove(0);
                }
                currentProces.decreaseCzasDoKoncaWykon(1);
                if (currentProces.getCzasDoKoncaWykon() <= 0) {
                    currentProces = null;
                }
            } else if (currentProces != null) {
                currentProces.decreaseCzasDoKoncaWykon(1);
                if (currentProces.getCzasDoKoncaWykon() <= 0) {
                    currentProces = null;
                }
            }
            czasOczKoncowy += availableProcesy.size();
            t++;
            updateAvailible(procesyCopy, availableProcesy, t);
        }

        return czasOczKoncowy;
    }

    public double RR() {
        ArrayList<Proces> procesyCopy = new ArrayList<>();
        for (Proces proces: procesy) {
            procesyCopy.add(new Proces(proces));
        }
        ArrayList<Proces> availableProcesy = new ArrayList<>();
        int t = 0, deltaT = 1, czasOczKoncowy = 0;
        //kwant czasu

        while (procesyCopy.size() != 0 || availableProcesy.size() != 0) {
            updateAvailible(procesyCopy, availableProcesy, t);
            if (availableProcesy.size() != 0) {
                for (int i = 0; i < availableProcesy.size(); i++) {
                    updateAvailible(procesyCopy, availableProcesy, t);
                    t += deltaT;
                    availableProcesy.get(i).decreaseCzasDoKoncaWykon(deltaT);
                    czasOczKoncowy += deltaT*(availableProcesy.size() - 1);
                    if (availableProcesy.get(i).getCzasDoKoncaWykon() <= 0) {
                        availableProcesy.remove(i);
                        i--;
                    }
                }
            }
            else {
                t++;
            }
        }

        return czasOczKoncowy;
    }

    private void updateAvailible(ArrayList<Proces> proc, ArrayList<Proces> aval, int t) {
        for (int i = 0; i < proc.size(); i++) {
            if (proc.get(i).getCzasPojawSystemie() <= t) {
                aval.add(proc.get(i));
                proc.remove(i);
                i--;
            }
        }
    }
}
