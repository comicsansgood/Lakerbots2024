package frc.robot.subsystems;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LedsSubsystem extends SubsystemBase{

    public AddressableLED m_led;
    private AddressableLEDBuffer m_ledBuffer;
    
    public LedsSubsystem(){
        m_led = new AddressableLED(0);
        m_ledBuffer = new AddressableLEDBuffer(66);
        m_led.setLength(m_ledBuffer.getLength());
        m_led.setData(m_ledBuffer);
        m_led.start();
    }

    public void setNode(int node, int r, int g, int b){
        m_ledBuffer.setRGB(node, r, g, b);
    }

    public double getStripLength(){
        return m_ledBuffer.getLength();
    }

    public void green(){
        for(var i = 0;i < m_ledBuffer.getLength(); i++){
            m_ledBuffer.setRGB(i, 0, 255, 0);
        }
    }

    public void red(){
        for(var i = 0;i < m_ledBuffer.getLength(); i++){
            m_ledBuffer.setRGB(i, 255, 0, 0);
        }
    }

    public void black(){
        for(var i = 0;i < m_ledBuffer.getLength(); i++){
            m_ledBuffer.setRGB(i, 0, 0, 0);
        }
    }

    public void yellow(){
        for(var i = 0; i < m_ledBuffer.getLength(); i++){
            m_ledBuffer.setRGB(i, 255, 255, 0);
        }
    }

    public void flashGreen(){
        for(var i = 0; i < 5; i++){
            green();
            Commands.waitSeconds(0.5);
            black();
            Commands.waitSeconds(0.2);
        }
        
    }

    public void spiritColors(){
        for(var i = 0;i < m_ledBuffer.getLength(); i++){
            if(i % 2 == 0){
            m_ledBuffer.setRGB(i, 255, 255, 255); //white
            }
            else{
                m_ledBuffer.setRGB(i, 0, 0, 255); //blue
            }
        }
    }

    public void redAndGreen(){
        for(var i = 0;i < m_ledBuffer.getLength(); i++){
            if(i % 2 == 0){
            m_ledBuffer.setRGB(i, 255, 0, 0); //white
            }
            else{
                m_ledBuffer.setRGB(i, 0, 255, 0); //blue
            }
        }
    }

    @Override
    public void periodic() {
        m_led.setData(m_ledBuffer);
    }
}
