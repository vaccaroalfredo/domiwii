package dashboard.web.model;

import java.util.EnumSet;

public class Command {

	private Integer status = -1; // Spento Acceso 0 1
	private Integer mode = -1; // Caldo freddo deumidificatore ventilatore
								// automatico 1-2-3-4-5
	private Integer temperature = -1; // 16 - 27
	private Integer speed = -1; // 1 a 4
	private Integer confort = -1; // 0 a 10

	final Integer MIN_TEMPERATURE = 16;
	final Integer MAX_TEMPERATURE = 27;
	final Integer MIN_SPEED = 1;
	final Integer MAX_SPEED = 4;
	final Integer MIN_CONFORT = 0;
	final Integer MAX_CONFORT = 10;
	final Integer MIN_MODE = 0;
	final Integer MAX_MODE = 5;
	final Integer DEFAULT_VALUE = -1;

	public Command(Integer status, Integer mode, Integer temperature, Integer speed, Integer confort) {
		super();

		this.status = status;
		this.mode = mode;
		this.temperature = temperature;
		this.speed = speed;
		this.confort = confort;

	}

	public Command() {
		super();

	}

	public void setParameters(String status, String mode, String temperature, String speed, String confort)
			throws Exception {

		Integer s = Integer.valueOf(status);
		Integer m = Integer.valueOf(mode);
		Integer t = Integer.valueOf(temperature);
		Integer sp = Integer.valueOf(speed);
		Integer c = Integer.valueOf(confort);

		boolean error = false;

		if (s == 1) {
			// acceso
			if (m >= this.MIN_MODE && m <= this.MAX_MODE) {

				this.mode = m;
			} else {

				error = true;

			}

			if (t >= this.MIN_TEMPERATURE && t <= this.MAX_TEMPERATURE || t == this.DEFAULT_VALUE ) {

				this.temperature = t;

			} else {

				error = true;

			}
			if (sp >= this.MIN_SPEED && sp <= this.MAX_SPEED || s == this.DEFAULT_VALUE ) {

				this.speed = sp;
			} else {
				error = true;

			}
			if (c >= this.MIN_CONFORT && c <= this.MAX_CONFORT || c == this.DEFAULT_VALUE ) {

				this.confort = c;

			} else {
				error = true;
			}
			this.status = 1;
		} else {
			// spento
			this.status = 0;
			this.mode = 0;
			this.temperature = 0;
			this.speed = 0;
			this.confort = 0;
		}

		if (error) {

			throw new Exception();

		}

	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getConfort() {
		return confort;
	}

	public void setConfort(Integer confort) {
		this.confort = confort;
	}

	public String buildCommand() {
		
		String toReturn = null;
		String t = String.valueOf(this.temperature);
		
		if ( this.status == 1 ) {
			if (this.mode == 5){
				
				toReturn = this.status+" "+this.mode+" "+0+" "+0+" "+0;
				
			}else{
				
				toReturn = this.status+" "+this.mode+" "+t.charAt(0)+" "+t.charAt(1)+" "+this.speed;
				
			}
		}else if (this.status == 0) {
			
			toReturn= "0 0 0 0 0";
			
		}
				
		
		return toReturn;
	}

}
