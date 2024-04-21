package de.tuda.dmdb.storage.exercise;

import de.tuda.dmdb.storage.AbstractDiskManager;
import de.tuda.dmdb.storage.AbstractPage;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.Record;
import de.tuda.dmdb.storage.types.exercise.SQLInteger;
import de.tuda.dmdb.storage.types.exercise.SQLVarchar;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Arrays;

import static de.tuda.dmdb.storage.AbstractPage.PAGE_SIZE;

/**
 * @author melhindi, danfai
 *     <p>The DiskManager takes care of the allocation and deallocation of pages within a database.
 *     It performs the reading and writing of pages to and from disk, providing a logical file layer
 *     within the context of a database management system.
 */
public class DiskManager extends AbstractDiskManager {

  public DiskManager() {
    super();

    // TODO open file
      File file = new File(DB_FILENAME);


  }

  @Override
  public void close() {
    // TODO close file
      try {
          RandomAccessFile randomAccessFile = new RandomAccessFile(DB_FILENAME,"rw");
          randomAccessFile.close();
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
  }

  @Override
  public AbstractPage readPage(Integer pageId) {
    // TODO implement me
      if(pageId < 0){
          return null;
      }

      try {
          RandomAccessFile randomAccessFile = new RandomAccessFile(DB_FILENAME,"rw");
          System.out.println("randomAccessFile.length()");
          System.out.println(randomAccessFile.length());

          if(randomAccessFile.length()< (long) pageId *PAGE_SIZE * 1024){
              return null;
          }

          byte[] b = new byte[PAGE_SIZE * 1024];
//          randomAccessFile.read(b,pageId*PAGE_SIZE * 1024,b.length);
          randomAccessFile.skipBytes(pageId*PAGE_SIZE * 1024);
          randomAccessFile.read(b,0,b.length);
          AbstractPage p2 = new RowPage(0);
          p2.deserialize(b);
          p2.setPageNumber(pageId);

          return p2;

      } catch (IOException e) {
          throw new RuntimeException(e);
      }


  }

    @Override
  public void writePage(AbstractPage page) {
    // TODO implement me

        if(page.getPageNumber() < 0){
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }



      try (RandomAccessFile randomAccessFile = new RandomAccessFile(DB_FILENAME,"rw")){

          int recNumber = page.getNumRecords();
//          System.out.println("page.toString()");
//          System.out.println(page.serialize()[0]);

          byte[] res = page.serialize();
          randomAccessFile.seek((long) page.getPageNumber() *PAGE_SIZE * 1024);
          randomAccessFile.write(res);


      } catch (IOException e) {
          throw new RuntimeException(e);
      }


  }
}
