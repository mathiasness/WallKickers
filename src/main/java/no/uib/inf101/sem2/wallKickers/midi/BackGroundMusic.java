package no.uib.inf101.sem2.wallKickers.midi;

import java.io.InputStream;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

/**
 * @author Torstein Strømme
 * Play the sound effect. Sample usage:
 * <code>
 * private BackGroundMusic music = new BackGroundMusic();
 * music.run(); 
 * </code>
 * free music from bitmidi:
 * Michal Jackson off the wall
 * https://bitmidi.com/michael-jackson-off-the-wall-mid
 */
public class BackGroundMusic implements Runnable {
    private static final String BACKGROUNDMUSIC = "michaelJacksonOffTheWall.mid";
    private Sequencer sequencer;

    @Override
    public void run() {
        InputStream song = BackGroundMusic.class.getClassLoader().getResourceAsStream(BACKGROUNDMUSIC);
        this.doPlayMidi(song, true);
    }

    private void doPlayMidi(final InputStream is, final boolean loop) {
        try {
            this.doStopMidiSounds();
            (this.sequencer = MidiSystem.getSequencer()).setSequence(MidiSystem.getSequence(is));
            if (loop) {
                this.sequencer.setLoopCount(-1);
            }
            this.sequencer.open();
            this.sequencer.start();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
    }

    public void doStopMidiSounds() {
        try {
            if (this.sequencer == null || !this.sequencer.isRunning()) {
                return;
            }
            this.sequencer.stop();
            this.sequencer.close();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
        this.sequencer = null;
    }

    public void doPauseMidiSounds() {
        try {
            if (this.sequencer == null || !this.sequencer.isRunning()) {
                return;
            }
            this.sequencer.stop();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
    }
    
    public void doUnpauseMidiSounds() {
        try {
            if (this.sequencer == null) {
                return;
            }
            this.sequencer.start();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
    }

    private void midiError(final String msg) {
        System.err.println("Midi error: " + msg);
        this.sequencer = null;
    }
}
