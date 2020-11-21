package com.InsuranceSystem.Coverage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import DB.AccidentDaoImpl;

public class AccidentListImpl implements AccidentList {

	ArrayList<Accident> accidentList = new ArrayList<Accident>();
	private AccidentDaoImpl accidentDaoImpl;

	public AccidentListImpl() {
		this.accidentDaoImpl = new AccidentDaoImpl();
		try {
			this.setAccidentList(accidentDaoImpl.selectAccidents());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean add(Accident accident) {
		// AccidentListImpl.accidentList.add(accident);
		try {
			this.accidentDaoImpl.insertAccidents(accident);
			System.out.println("입력이 완료되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			this.setAccidentList(accidentDaoImpl.selectAccidents());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int selectID) {

		Iterator<Accident> accidentIterator = accidentList.iterator();
		while (accidentIterator.hasNext()) {
			Accident accident = accidentIterator.next();
			if (accident.getCustomerID() == selectID) {
				try {
					accidentDaoImpl.deleteAccident(getAccident(selectID));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					accidentDaoImpl.selectAccidents();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				accidentList.remove(getAccident(selectID));
				break;
			}
		}

		return false;
	}

	public Accident show() {
		if (accidentList.size() != 0) {
			for (Accident accident : accidentList) {
				System.out.print("고객 ID : " + accident.getCustomerID() + " ");
				System.out.print("고객 이름 : " + accident.getName() + " ");
				System.out.print("사고 장소 : " + accident.getLocation() + " ");
				System.out.print("사고 날짜 : " + accident.toStringAccidentDate() + " ");
				System.out.print("입원 여부 : " + accident.isHospitalization() + " ");
				System.out.print("입원 장소 : " + accident.getHospitalPlace() + " ");
				System.out.println("사고 원인 : " + accident.getAccidentCause() + " ");

			}
		} else {
			System.out.println("현재 발생한 사고가 없습니다.");
		}

		return null;
	}

	public Accident getAccident(int selectID) {
		for (Accident accident : accidentList) {
			if (accident.getCustomerID() == selectID) {
				return accident;
			} else {
				System.out.println("해당 사고가 없습니다.");
			}
		}

		return null;
	}

	public Accident getCusomter(int customerID) {
		for (Accident accident : accidentList) {
			if (accident.getCustomerID() == customerID) {
				return accident;
			}
		}
		return null;
	}

	public Accident makeFile(int selectID) {
		Accident accident = getAccident(selectID);
		String[] accidentArr = new String[7];
		accidentArr[0] = "고객ID : " + String.valueOf(accident.getCustomerID());
		accidentArr[1] = "고객명 : " + accident.getName();
		accidentArr[2] = "사고 장소 : " + accident.getLocation();
		accidentArr[3] = "사고 날짜 : " + accident.toStringAccidentDate();
		accidentArr[4] = "입원 여부 : " + accident.isHospitalization();
		accidentArr[5] = "입원 장소 : " + accident.getHospitalPlace();
		accidentArr[6] = "사고 원인 : " + accident.getAccidentCause();

		String text = "";
		for (int i = 0; i < accidentArr.length; i++) {
			text += accidentArr[i] + "\n";
		}
		String fileName = "./accidentFile\\" + accident.toStringAccidentDate() + "."
				+ accident.getCustomerID() + "." + accident.getName() + ".txt";

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));
			bw.write(text);
			bw.flush();

			bw.close();
			System.out.println(accident.getName() + "고객님의 손해조사파일이 생성되었습니다.");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	public ArrayList<Accident> getAccidentList() {
		return accidentList;
	}

	public void setAccidentList(ArrayList<Accident> accidentList) {
		this.accidentList = accidentList;
	}

}