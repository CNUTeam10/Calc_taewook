package univ.lecture.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tchi on 2017. 4. 1..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cal {
    private int teamId;
    private long now;
    private double result;
    
    public void setTeamId(int teamId)
    {
    	this.teamId = teamId;
    }
    
    public void setNow(long now)
    {
    	this.now = now;
    }
    
    public void setResult(double result)
    {
    	this.result = result;
    }
}
